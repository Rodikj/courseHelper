<<<<<<< HEAD
## DEPRECATED!! USING LANGGRAPH AGENTIC NOW

import os
import time
from google import genai
from app.models.request import AIRequest, UriRequest
from app.models.conversation import Message
from app.models.video import GeminiUriProvider
from app.utils.time_util import convert_seconds_to_minutes, get_youtube_video_duration, get_file_video_duration
from app.utils.conversation_util import convert_conversation_to_google_format, convert_conversation_to_langchain_format
from app.utils.model_config_util import generate_model_config_for_gemini
from langchain_google_genai import ChatGoogleGenerativeAI
=======
import os
import time
from google import genai
from google.genai import types
from moviepy import VideoFileClip
from app.models.request import AIRequest, UriRequest
from app.models.conversation import Message, ConversationHistory
from app.models.enums import VideoType, UriProvider
from app.models.video import Video, Duration, GeminiUriProvider
from app.utils.time_util import convert_seconds_to_minutes, get_youtube_video_duration, get_file_video_duration
>>>>>>> 143d6af (Initial Python service commit)

class GeminiService:

    @staticmethod
    def upload_video_to_gemini(request:UriRequest):
        file_path = request.video.path

        # Check file existence
        if not os.path.exists(file_path):
            raise FileNotFoundError(f"File not found: {file_path}")

        # Check file size
        file_size = os.path.getsize(file_path)
        if file_size == 0:
            raise ValueError(f"File is empty: {file_path}")

        client = genai.Client(api_key=request.api_key if request.api_key is not None else os.environ.get("GEMINI_API_KEY"))
        try:
            myfile = client.files.upload(file=request.video.path)
        except Exception as e:
            return {"error": f"Error uploading file to Gemini's Side: {str(e)}"}

        while myfile.state.name == "PROCESSING":
            time.sleep(5)  # Wait 5 seconds between checks
            myfile = client.files.get(name=myfile.name)
        
        request.video.uri = myfile.uri

        if request.video.uri_data is None:
            request.video.uri_data = GeminiUriProvider(uri_file_name=myfile.name, uri_state=myfile.state.name, uri_mime_type=myfile.mime_type)

        request.video.duration = convert_seconds_to_minutes(float(myfile.video_metadata['videoDuration'].replace('s', '')))

        return request.model_dump()

    @staticmethod
    def process(request: AIRequest):
        """Process AI request using Gemini while maintaining history"""

        api_key = request.api_key if request.api_key else os.environ.get("GEMINI_API_KEY")
        client = genai.Client(api_key=api_key)
<<<<<<< HEAD

        # Convert conversation history to Gemini format
        parts = convert_conversation_to_google_format(request)
        generate_content_config = generate_model_config_for_gemini(request)
=======
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
                            types.Part.from_uri(file_uri=msg.video.uri, mime_type="video/*" if msg.video.uri_data.uri_mime_type is None else msg.video.uri_data.uri_mime_type),
                            part,
                        ]
                    parts.append(types.Content(role="user", parts=part))

                elif msg.role == "model":
                    parts.append(types.Content(role="model", parts=[types.Part.from_text(text=msg.content)]))

        # Configure model settings
        generate_content_config = types.GenerateContentConfig(
            temperature=0.6,
            top_p=0.95,
            top_k=40,
            max_output_tokens=8192,
            response_mime_type="text/plain",
            system_instruction=[types.Part.from_text(text=request.system_instruction_text)] if request.system_instruction_text else None,
        )
>>>>>>> 143d6af (Initial Python service commit)

        # Call Gemini AI
        try:
            result = client.models.generate_content(
                model=request.model_name,
                contents=parts,
                config=generate_content_config,
            )

            if not result or not result.candidates:
                raise ValueError("No response generated by Gemini.")

            ai_response = result.text # Extract AI response

            new_message = Message(role="model", content=ai_response)

            updated_history = request.conversation_history.messages + [new_message]

            return {"conversation_history": [msg.model_dump() for msg in updated_history]}

        except Exception as e:
            return {"error": str(e)}
<<<<<<< HEAD

    @staticmethod
    def process_langchain(request: AIRequest):
        """Process AI request using Gemini with Langchain"""

        api_key = request.api_key if request.api_key else os.environ.get("GEMINI_API_KEY")
        model = ChatGoogleGenerativeAI(model=request.model_name, temperature=0.6, max_output_tokens=8192, api_key=api_key)

        conversation_list = convert_conversation_to_langchain_format(request)

        result = model.invoke(conversation_list)

        new_message = Message(role="model", content=result.content)

        updated_history = request.conversation_history.messages + [new_message]

        return {"conversation_history": [msg.model_dump() for msg in updated_history]}
=======
>>>>>>> 143d6af (Initial Python service commit)
