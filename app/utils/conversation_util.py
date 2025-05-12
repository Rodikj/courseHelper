from langchain_core.messages import HumanMessage, SystemMessage, AIMessage
from app.models.conversation import ConversationHistory 
from app.models.request import AIRequest
from app.models.enums import VideoType, UriProvider
from app.models.video import Video, Duration, GeminiUriProvider
from app.utils.time_util import convert_seconds_to_minutes, get_youtube_video_duration, get_file_video_duration
from google.genai import types
from typing import Dict

def convert_conversation_to_google_format(request: AIRequest) -> list:
    parts = []  # Structured conversation history

    if request.conversation_history:
        for msg in request.conversation_history.messages:
            if msg.role == "user":
                part = [types.Part.from_text(text=msg.content)]

                if msg.video:

                    if msg.video.duration is None:
                        if msg.video.type == VideoType.YOUTUBE_VIDEO:
                            msg.video.duration = Duration()
                            duration = get_youtube_video_duration(video_uri=msg.video.uri)
                        elif msg.video.type == VideoType.FILE_VIDEO:
                            msg.video.duration = Duration()
                            duration = get_file_video_duration(file_path=msg.video.path)
                        msg.video.duration.minutes = duration["minutes"]
                        msg.video.duration.seconds = duration["seconds"]

                    part = types.Part.from_text(text=f"{msg.content}\n\nVideo Duration: {msg.video.duration.minutes}m {msg.video.duration.seconds}s")
                    part = [
                        types.Part.from_uri(file_uri=msg.video.uri, mime_type="video/*" if msg.video.uri_data is None else msg.video.uri_data.uri_mime_type),
                        part,
                    ]

                parts.append(types.Content(role="user", parts=part))

            elif msg.role == "model":
                parts.append(types.Content(role="model", parts=[types.Part.from_text(text=msg.content)]))
    return parts

def convert_conversation_to_langchain_format(request: AIRequest) -> list:
    conversation_list = []
    mime_type = None

    if request.system_instruction_text:
        conversation_list.append(SystemMessage(content=request.system_instruction_text))

    for msg in request.conversation_history.messages:
        if msg.role == "user":
            message = HumanMessage(content=msg.content)

            if msg.video:

                if msg.video.duration is None:
                    if msg.video.type == VideoType.YOUTUBE_VIDEO:
                        msg.video.duration = Duration()
                        duration = get_youtube_video_duration(video_uri=msg.video.uri)
                    elif msg.video.type == VideoType.FILE_VIDEO:
                        msg.video.duration = Duration()
                        duration = get_file_video_duration(file_path=msg.video.path)
                    msg.video.duration.minutes = duration["minutes"]
                    msg.video.duration.seconds = duration["seconds"]

                mime_type = "video/*" if msg.video.uri_data is None else msg.video.uri_data.uri_mime_type

                message = f"{msg.content}\n\nVideo Duration: {msg.video.duration.minutes}m {msg.video.duration.seconds}s"
                message = HumanMessage(content=[
                        {
                            "type": "text",
                            "text": message
                        },
                        {
                            "type": "media",
                            "mime_type": mime_type,
                            "file_uri": msg.video.uri
                        },
                    ]
                )

            conversation_list.append(message)
                
        elif msg.role == "model":
            conversation_list.append(AIMessage(content=msg.content))

    return conversation_list

def convert_conversation_to_agent_format(request: AIRequest) -> Dict[str, list]:
    langchain_format_conversation = convert_conversation_to_langchain_format(request)
    agent_format_conversation = {"messages": langchain_format_conversation}

    return agent_format_conversation

def convert_agent_format_to_conversation(messages: Dict[str, list]) -> ConversationHistory:
    conversation_history = ConversationHistory()
    conversation_history.messages = []

    for message in messages["messages"]:
        if isinstance(message, HumanMessage):
            role = "user"
        elif isinstance(message, AIMessage):
            role = "model"

        else:
            continue

        conversation_history.messages.append(
            {
                "role": role,
                "content": message.content,
                "video": None,
            }
        )

    return conversation_history
    