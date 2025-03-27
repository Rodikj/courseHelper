from pydantic import BaseModel, Field
from typing import List, Literal, Optional
from app.models.video import Video

class Message(BaseModel):
    """Schema for a single conversation message"""
    role: Literal["user", "model"]
    content: str = Field(description="The content of a message.", min_length=1, example="Hello, what is Python?")
    video: Optional[Video] = Field(default=None, example="https://www.youtube.com/watch?v=fWjsdhR3z3c")

class ConversationHistory(BaseModel):
    """Schema for conversation history"""
    messages: List[Message] = Field(..., example=[
        {
            "role": "user",
            "content": "Describe this video.",
            "video": {
                "type": "YOUTUBE_VIDEO",
                "uri": "https://www.youtube.com/watch?v=9bZkp7q19f0",
                "duration": {"minutes": 4, "seconds": 12}
            }
        },
        {
            "role": "model",
            "content": "Here is a description of the video..."
        }
    ])
