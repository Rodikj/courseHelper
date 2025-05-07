from fastapi import FastAPI, APIRouter, UploadFile
from celery.result import AsyncResult
from app.models.request import AIRequest
from celery_worker import (
    process_ai_request_task,
    upload_file_task,
    create_video_uri_using_gemini_task,
    parse_summarize_ingest_pdf_task
    )  
import uuid
from app.models.request import UriRequest
from app.models.enums import UriProvider
from typing import Optional

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
async def upload_file(file: UploadFile, api_key: Optional[str] = None):
    """
    Endpoint to handle file uploads
    
    Args:

        file (UploadFile): Uploaded file object

        api_key (Optional[str]): API key for summarisation and ingestion in Qdrant vector database (Optional: only for PDF files Google Studio API needed)
        
    Returns:
        dict: File upload information
    """
    file_id = str(uuid.uuid4())  # Create a unique ID for the file
    
    # Read file content into memory
    file_content = await file.read()
    
    # Trigger Celery task with file content
    task = upload_file_task.apply_async(args=[file_id, file.filename, file_content])

    return_obj = {"filename": file.filename, "file_id": file_id, "task_id": task.id}

    if file.content_type=="application/pdf" and file.filename.lower().endswith(".pdf"):
        parse_summarise_ingest_task = parse_summarize_ingest_pdf_task.apply_async(args=[file_id, file.filename, file_content, api_key])
        return_obj["parse_summarise_ingest_task_id"] = parse_summarise_ingest_task.id
    
    return return_obj

@router.post("/get_video_uri")
async def get_video_uri_route(request: UriRequest):
    if request.uri_provider == UriProvider.GEMINI_URI_PROVIDER:
        if request.api_key is None:
            raise Exception("An API key is needed for the Gemini URI provider.")
        task = create_video_uri_using_gemini_task.apply_async(args=[request.model_dump()])
    return {"task_id": task.id, "status": "PENDING"}
