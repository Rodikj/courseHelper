import os
from pydantic_settings import BaseSettings
from typing import Optional, Dict, Any, List
from functools import lru_cache
from pydantic import field_validator


class Settings(BaseSettings):
    # App Configuration
    timeout: int = 1000  # Your custom field

    APP_NAME: str = "AI Content Analyzer"
    APP_VERSION: str = "0.1.0"
    DEBUG: bool = True
    
    # API Configuration
    API_PREFIX: str = "/api"
    
    # AI Service Configuration
    DEFAULT_AI_PROVIDER: str = "gemini"
    GEMINI_API_KEY: Optional[str] = os.environ.get("GEMINI_API_KEY")

    UPLOAD_DIR: str = "uploads"
    
    # Default model parameters
    DEFAULT_MODEL_PARAMS: Dict[str, Any] = {
        "gemini": {
            "model": "gemini-1.5-flash",
            "temperature": 0.6,
            "top_p": 0.95,
            "top_k": 40,
            "max_output_tokens": 8192,
        }
    }
    
    # System prompts for different providers
    SYSTEM_PROMPTS: Dict[str, str] = {
        "gemini": "You are a helpful AI that answers questions about educational content."
    }
    
    # Redis for Celery and history
    REDIS_HOST: str = os.environ.get("REDIS_HOST", "localhost")
    REDIS_PORT: int = os.environ.get("REDIS_PORT", 6379)
    REDIS_DB: int = os.environ.get("REDIS_DB", 0)
    
    # History settings
    MAX_HISTORY_LENGTH: int = 10
    
    class Config:
        env_file = ".env"
        extra = "allow"


@lru_cache()
def get_settings():
    return Settings()