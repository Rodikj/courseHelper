from app.models.video import Video
from langchain_core.messages import HumanMessage, SystemMessage

def prompt_flash_card_generation(texts: list) -> str:
    prompt = (
        "Based on the following texts, generate 5 random but meaningful questions and answers in JSON format:\n\n"
        + '\n\n'.join(texts)
        + "\n\nRespond in JSON format like this:\n"
        + '[\n  {"question": "Your question here", "answer": "Your answer here"}\n]'
    )
    return prompt

def prompt_flash_card_generation_from_video(video: Video) -> str:
    if isinstance(video,dict):
        video = Video(**video)
    mime_type = "video/*" if video.uri_data is None else video.uri_data.uri_mime_type
    message = (
        "Based on the following video, generate 5 random but meaningful questions and answers in JSON format:\n\n"
        + "\n\nRespond in JSON format like this:\n"
        + '[\n  {"question": "Your question here", "answer": "Your answer here"}\n]'
    )
    prompt = [SystemMessage("You are an assistant."),
              HumanMessage(content=[
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
                )]
    return prompt