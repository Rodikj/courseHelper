from celery_config import celery_app
from app.models.request import AIRequest, UriRequest
from app.services.provider_service import get_ai_response, upload_file_to_gemini
import shutil
from app.core.config_settings import get_settings
import os
from app.models.video import Video, GeminiUriProvider
from app.models.enums import VideoType
from google import genai
import asyncio
from app.utils.time_util import convert_seconds_to_minutes
import time

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