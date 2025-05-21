from celery_config import celery_app
from app.models.request import AIRequest, UriRequest
from app.services.provider_service import get_ai_response, upload_file_to_gemini
from app.services.flash_card_service import create_flash_cards_service
from app.services import parser_service, summarisation_service, ingestion_service
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_google_genai.embeddings import GoogleGenerativeAIEmbeddings
import shutil
from app.core.config_settings import get_settings
import os
from app.models.video import Video, GeminiUriProvider
from app.models.enums import VideoType
from app.models.flash_cards import FlashCardList
from google import genai
import asyncio
from app.utils.time_util import convert_seconds_to_minutes
import time
from typing import Optional

settings = get_settings()

@celery_app.task(name="process_ai_request")
def process_ai_request_task(request_data: dict):
    """Celery task to process AI requests asynchronously"""
    request = AIRequest(**request_data)  # Deserialize request
    response = get_ai_response(request)  # Process request
    return response if isinstance(response, dict) else response.model_dump()

@celery_app.task(name="upload_file_task")
def upload_file_task(file_id: str, filename: str, file_content: bytes):
    """
    Task to handle file upload directly with file content
    
    Args:
        file_id (str): Unique identifier for the file
        filename (str): Original filename
        file_content (bytes): File content as bytes
    """
    upload_dir = settings.UPLOAD_DIR
    os.makedirs(upload_dir, exist_ok=True)
    
    # Final destination path
    file_name = f"{file_id}_{filename}"
    upload_dir_abs = os.path.abspath(upload_dir)

    # Join it with the file name
    file_path = os.path.join(upload_dir_abs, file_name)
    
    # Write file content directly
    with open(file_path, "wb") as buffer:
        buffer.write(file_content)
    
    # Further processing can go here (e.g., data extraction, analysis)
    return {"filename": filename, "file_id": file_id, "path": str(file_path)}

@celery_app.task(name="create_video_uri_using_gemini", time_limit=1200, soft_time_limit=1000)
def create_video_uri_using_gemini_task(request_data: dict):

    request = UriRequest(**request_data)

    response = upload_file_to_gemini(request)

    return response if isinstance(response, dict) else request.video.model_dump()

@celery_app.task(name = "parse_summarize_ingest_pdf")
def parse_summarize_ingest_pdf_task(file_id: str,
                                    file_name: str,
                                    file_content: bytes,
                                    api_key: Optional[str] = None,
                                    file_type: Optional[str] = None):
    """
    Task to parse, summarize and ingest PDF files.
    
    Args:
        file_path (str): Path to the PDF file
        file_id (str): Unique identifier for the file
        file_name (str): Original filename
    """
    #Parse
    parsed_result = parser_service.parse_pdf(file_path="random", file_content=file_content, file_type=file_type)

    texts = parser_service.get_texts_elements(parsed_result)
    tables = parser_service.get_table_elements(parsed_result)
    images = parser_service.get_image_elements_base64(parsed_result)

    #Summarise
    model = ChatGoogleGenerativeAI(temperature=0.5,
                               model="gemini-1.5-flash",
                               api_key=api_key,
                               )
    summariser = summarisation_service.Summarisation(model=model)

    summarised_texts = summariser.summarise_chunks(
        container=texts,
        extract_input=lambda chunk: chunk.content,  # or just `str(chunk.content)` if `.text` doesn't exist
        summarization_chain=summariser.summarize_texts_tables_chain
    )

    summarised_images = summariser.summarise_chunks(
        container=images,
        extract_input=lambda chunk: chunk.base64,  
        summarization_chain=summariser.summarize_images_chain
    )

    summarised_tables = summariser.summarise_chunks(
        container=tables,
        extract_input=lambda chunk: chunk.html,  # or just `str(chunk.content)` if `.text` doesn't exist
        summarization_chain=summariser.summarize_texts_tables_chain
    )

    ingestion_embedding_model = GoogleGenerativeAIEmbeddings(model="models/text-embedding-004",
                                            google_api_key=api_key,
                                            task_type="retrieval_document")
    
    ingestion = ingestion_service.Ingestion(embedding_model=ingestion_embedding_model,
                                        texts=summarised_texts,
                                        tables=summarised_tables,
                                        images=summarised_images,
                                        qdrant_collection_name=file_id,
                                        )
    flash_cards = create_flash_cards_service(collection_name=file_id, api_key=api_key)
    return {"qdrant_collection_name": file_id, "flash_cards": flash_cards.to_json_serializable() if isinstance(flash_cards, FlashCardList) else flash_cards}

@celery_app.task(name = "create_flash_cards")
def create_flash_cards_task(api_key: str, qdrant_collection_name: Optional[str] = None, video: Optional[Video] = None):
    if qdrant_collection_name and video:
        return {"error": "Both collection and video provided, only provide a singular one, video or collection name."}
    elif qdrant_collection_name:
        flash_cards = create_flash_cards_service(collection_name=qdrant_collection_name, api_key=api_key)
    elif video:
        flash_cards = create_flash_cards_service(video=video, api_key=api_key)
    else:
        return {"error": "No collection name or video provided."}
    return flash_cards if isinstance(flash_cards, dict) else flash_cards.model_dump()