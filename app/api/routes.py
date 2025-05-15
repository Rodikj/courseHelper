from fastapi import FastAPI, APIRouter, UploadFile, Form, HTTPException
from pydantic import ValidationError
from celery.result import AsyncResult
from app.models.request import AIRequest
from app.models.video import Video
from app.services.flash_card_service import create_flash_cards_service
from celery_worker import (
    process_ai_request_task,
    upload_file_task,
    create_video_uri_using_gemini_task,
    parse_summarize_ingest_pdf_task,
    create_flash_cards_task
    )  
import uuid
from app.models.request import UriRequest
from app.models.enums import UriProvider, FileTypeFastAPI
# from app.utils.upload_video_dependency_util import video_json_dependency
from typing import Optional
from app.models.flash_cards import FlashCardList
import json

router = APIRouter()

@router.post("/process-ai")
async def process_ai_request(request: AIRequest):
    """API endpoint to process AI requests asynchronously"""
    task = process_ai_request_task.apply_async(args=[request.model_dump()])  # Call apply_async here
    return {"task_id": task.id, "status": "PENDING"}

@router.get("/task-status/{task_id}")
async def get_task_status(task_id: str):
    """Check the status of a Celery task"""
    task_result = AsyncResult(task_id)  # Directly check status without a Celery task
    return {"task_id": task_id, "status": task_result.status, "result": task_result.result}

@router.post("/upload/")
async def upload_file(video: Optional[str] = Form(None), file: Optional[UploadFile] = None, api_key: Optional[str] = None):
    """
    Endpoint to handle file uploads
    
    Args:

        api_key (Optional[str]): API key for summarisation and ingestion in Qdrant vector database, and generation of Flash Cards. 

        file (UploadFile): Uploaded file object

        video (Optional[Video]): Video object SPECIFICALLY WILL ONLY BE USED TO GENERATE FLASH CARDS!! SO ONLY EITHER UPLOAD FILE OR ADD VIDEO, NOT BOTH!
        In SwaggerUI at localhost:8000/docs, this will be shown as a string upload, but add a valid Video object in JSON format.
        Example:
        {
            "type": "YOUTUBE_VIDEO",
            "uri": "https://www.youtube.com/watch?v=fWjsdhR3z3c"
        }
        
    Returns:
        dict: File upload information
    """
    if file is not None:
        file_id = str(uuid.uuid4())  # Create a unique ID for the file
        
        # Read file content into memory
        file_content = await file.read()
        
        # Trigger Celery task with file content
        task = upload_file_task.apply_async(args=[file_id, file.filename, file_content])

        return_obj = {"filename": file.filename, "file_id": file_id, "task_id": task.id}

        if (file.content_type==FileTypeFastAPI.PDF and file.filename.lower().endswith(".pdf")) or (file.content_type == FileTypeFastAPI.WORD and file.filename.lower().endswith(".docx")):
            parse_summarise_ingest_task = parse_summarize_ingest_pdf_task.apply_async(args=[file_id, file.filename, file_content, api_key, file.content_type])
            return_obj["parse_summarise_ingest_task_id"] = parse_summarise_ingest_task.id
        
        return return_obj
    
    elif video is not None:
        # Flash cards creation logic (replace with your function)
        try:
            video_obj = Video(**json.loads(video))  # Parse JSON manually
        except json.JSONDecodeError:
            raise HTTPException(status_code=400, detail="Invalid JSON in 'video' field")
        
        flash_cards = create_flash_cards_service(api_key=api_key, video=video_obj)
        return_obj = {"video": video_obj if isinstance(video,dict) else video_obj.model_dump(),
                      "flash_cards": flash_cards.to_json_serializable() if isinstance(flash_cards, FlashCardList) else flash_cards}
        return return_obj
    else:
        return_obj = {"error": "No file or video provided."}
        return return_obj
    


@router.post("/get_video_uri")
async def get_video_uri_route(request: UriRequest):
    if request.uri_provider == UriProvider.GEMINI_URI_PROVIDER:
        if request.api_key is None:
            raise Exception("An API key is needed for the Gemini URI provider.")
        task = create_video_uri_using_gemini_task.apply_async(args=[request.model_dump()])
    return {"task_id": task.id, "status": "PENDING"}

@router.post("/create_flash_cards")
async def create_flash_cards_route(collection_name: Optional[str] = None, api_key: Optional[str] = None, video: Optional[Video] = None):
    """
    Endpoint to handle creation of flash cards. KEEP IN MIND: 5 flash cards are created in question/answer pairs.
    
    KEEP IN MIND: Flash cards are also created when uploading a file in the parse-summarise-ingest task.

    Args:

        video (Optional[Video]): Video object SPECIFICALLY WILL ONLY BE USED TO GENERATE FLASH CARDS!! SO ONLY EITHER UPLOAD COLLECTION NAME OR ADD VIDEO, NOT BOTH!

        collection_name (str): the Qdrant collection name

        api_key (str): Gemini API key since we're using their models

    Returns:
        dict: flash_cards  
    """
    task = create_flash_cards_task.apply_async(args=[api_key, collection_name, video.model_dump() if video else None])
    return {"task_id": task.id, "status": "PENDING"}