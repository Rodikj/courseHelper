from app.models.enums import ModelProvider, UriProvider
from app.services.api_provider_implementations.gemini_service import GeminiService
<<<<<<< HEAD
from app.services.api_provider_implementations.agent_service import AgentService
=======
>>>>>>> 143d6af (Initial Python service commit)
from app.models.request import AIRequest, UriRequest

def get_ai_response(request_data: AIRequest):
    """Handles AI processing based on the selected model provider."""

    model_type = request_data.model_provider
    
    if model_type == ModelProvider.GEMINI.value:
<<<<<<< HEAD
        service = AgentService()
    else:
        raise ValueError(f"Unsupported model type: {model_type}")

    return service.process_agent(request_data)
=======
        service = GeminiService()
    else:
        raise ValueError(f"Unsupported model type: {model_type}")

    return service.process(request_data)
>>>>>>> 143d6af (Initial Python service commit)

def upload_file_to_gemini(request: UriRequest):
    """Uploads a file to Gemini based on the URI provider."""
    
    uri_provider = request.uri_provider

    if uri_provider == UriProvider.GEMINI_URI_PROVIDER.value:
        service = GeminiService()
    else:
        raise ValueError(f"Unsupported URI provider: {uri_provider}")
    
    return service.upload_video_to_gemini(request)