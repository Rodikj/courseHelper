from pydantic import BaseModel, Field
from typing import List, Literal, Optional
from app.models.video import Video
<<<<<<< HEAD
from langchain_core.messages import ToolMessage

class Message(BaseModel):
    """Schema for a single conversation message"""
    role: Literal["user", "model", "tool"]
    content: str = Field(description="The content of a message.", min_length=1, example="Hello, what is Python?")
    video: Optional[Video] = Field(default=None, example="https://www.youtube.com/watch?v=fWjsdhR3z3c")
    tool_calls: Optional[List[dict]] = Field(default=None, example=[{"tool": "multiply", "args": [2, 3]}])
    tool_results: Optional[List[ToolMessage]] = None
=======

class Message(BaseModel):
    """Schema for a single conversation message"""
    role: Literal["user", "model"]
    content: str = Field(description="The content of a message.", min_length=1, example="Hello, what is Python?")
    video: Optional[Video] = Field(default=None, example="https://www.youtube.com/watch?v=fWjsdhR3z3c")
>>>>>>> 143d6af (Initial Python service commit)

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
