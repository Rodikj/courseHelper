from google.genai import types
from app.models.request import AIRequest

def generate_model_config_for_gemini(request:AIRequest) -> types.GenerateContentConfig:
    # Configure model settings
    generate_content_config = types.GenerateContentConfig(
        temperature=0.6,
        top_p=0.95,
        top_k=40,
        max_output_tokens=8192,
        response_mime_type="text/plain",
        system_instruction=[types.Part.from_text(text=request.system_instruction_text)] if request.system_instruction_text else None,
    )
    return generate_content_config