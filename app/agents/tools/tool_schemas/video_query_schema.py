from pydantic import BaseModel, Field

class VideoQuerySchema(BaseModel):
    video_question: str = Field(..., description="The question about the video content.")