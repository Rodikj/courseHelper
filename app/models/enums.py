from enum import Enum

class ModelProvider(str, Enum):
    """Supported AI Model Providers"""
    GEMINI = "gemini"
    OPEN_ROUTER = "open_router"
    GROQ = "groq"

class VideoType(str, Enum):
    """Types of Video Sources"""
    YOUTUBE_VIDEO = "YOUTUBE_VIDEO"
    FILE_VIDEO = "FILE_VIDEO"

class FileType(str, Enum):
    """Supported file types in a course"""
    PDF = "PDF"
    POWERPOINT = "POWERPOINT"
    VIDEO = "VIDEO"

class UriProvider(str, Enum):
    """Supported URI providers"""
    GEMINI_URI_PROVIDER = "gemini_uri_provider"

class GeminiUriState(str,Enum):
    PROCESSING = "PROCESSING"
    ACTIVE = "ACTIVE"
    FAILED = "FAILED"

class FileTypeFastAPI(str, Enum):
    """Supported file types in a course"""
    PDF = "application/pdf"
    WORD = "application/vnd.openxmlformats-officedocument.wordprocessingml.document"