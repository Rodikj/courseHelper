##CURRENTLY ONLY WORKS WITH GEMINI MODEL!

from langchain_core.tools import Tool
from app.agents.tools.tool_schemas.video_query_schema import VideoQuerySchema
from app.models.video import Video, Duration
from app.models.enums import VideoType
from app.utils.time_util import get_youtube_video_duration, get_file_video_duration
from langchain_core.messages import HumanMessage
from langchain_core.language_models.chat_models import BaseChatModel

def create_video_answerer_tool(video: Video, llm: BaseChatModel) -> Tool:
    """
    Creates a video answerer tool with the given video URI.
    
    Args:
        video_uri (str): The URI of the video to be answered.
    
    Returns:
        Tool: A tool for answering questions about the video.
    """
    def _video_answerer(video_question: str) -> str:
        """
        Use this tool to respond to any user input mentioning a video, even if they do not explicitly ask a question.
    
        This includes requests to describe, explain, summarize, or provide details about any video content. 
        Trigger this tool whenever the user input contains any reference to a video, clip, footage, scene, or visual content.
        
        Args:
            video_question (str): The question about the video content.
        
        Returns:
            str: The answer to the user's question.
        """
        # Placeholder for actual video processing and answering logic

        if video.duration is None:
            if video.type == VideoType.YOUTUBE_VIDEO:
                video.duration = Duration()
                duration = get_youtube_video_duration(video_uri=video.uri)
            elif video.type == VideoType.FILE_VIDEO:
                video.duration = Duration()
                duration = get_file_video_duration(file_path=video.path)
            video.duration.minutes = duration["minutes"]
            video.duration.seconds = duration["seconds"]

        mime_type = "video/*" if video.uri_data is None else video.uri_data.uri_mime_type

        message = f"{video_question}\n\nVideo Duration: {video.duration.minutes}m {video.duration.seconds}s"
        message = HumanMessage(content=[
                {
                    "type": "text",
                    "text": message
                },
                {
                    "type": "media",
                    "mime_type": mime_type,
                    "file_uri": video.uri
                },
            ]
        )

        llm_response = llm.invoke([message])

        return llm_response.content
    
    return Tool(
        name="video_answerer",
        description="""
        Use this tool to respond to any user input mentioning a video, even if they do not explicitly ask a question.
    
        This includes requests to describe, explain, summarize, or provide details about any video content. 
        Trigger this tool whenever the user input contains any reference to a video, clip, footage, scene, or visual content.
        
        Args:
            video_question (str): The question about the video content.
        
        Returns:
            str: The answer to the user's question.
        """,
        func=_video_answerer,
        args_schema=VideoQuerySchema,
    )