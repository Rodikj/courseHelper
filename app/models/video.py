from typing import List, Optional
from pydantic import BaseModel, Field
from app.models.enums import VideoType, UriProvider, GeminiUriState

class Duration(BaseModel):
    """Schema for video duration"""
    minutes: float = Field(0, description="Number of minutes", example=30)
    seconds: float = Field(0, description="Number of seconds", example=4)

class GeminiUriProvider(BaseModel):
    uri_file_name: str = Field(..., description="The file name of the Gemini uploaded file", example="ABCDEFG")
    uri_state: GeminiUriState = Field(..., description="The state of the URI", example="ACTIVE / PROCESSING / FAILED")
    uri_mime_type: str = Field(..., description="The MIME type of the URI", example="video/mp4")


class Video(BaseModel):
    """Schema for a video"""
    type: VideoType = Field(..., description="The type of a video", example="YOUTUBE_VIDEO")
    uri: Optional[str] = Field(default=None, description="The URI of a video", example="https://www.youtube.com/watch?v=fWjsdhR3z3c")
    uri_data: Optional[GeminiUriProvider] = Field(default=None, description="The URI data of a video, if the video is of type <FILE_VIDEO> and provider is GEMINI")
    path: Optional[str] = Field(description="Video path when the video type is <FILE_VIDEO>.", default=None, example="././uploads/video_name.mp4")
    duration: Optional[Duration] = Field(default=None, description="Dict type containing 'minutes' and 'seconds' of video. Optional.")