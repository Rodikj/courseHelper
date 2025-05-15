from app.core.config_settings import get_settings
from app.models import flash_cards
from app.models.video import Video
from app.utils.qdrant_vector_database_util import get_random_data_from_qdrant
from app.prompts.prompt_flash_card_generation import prompt_flash_card_generation, prompt_flash_card_generation_from_video
import json
import re
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_google_genai.embeddings import GoogleGenerativeAIEmbeddings
from typing import Optional
from qdrant_client import QdrantClient

settings = get_settings()
qdrant_client = QdrantClient(host=settings.QDRANT_HOST, port=settings.QDRANT_PORT)

def generate_questions_and_answers(collection_name: str, api_key: str) -> list:
    texts = get_random_data_from_qdrant(qdrant_client=qdrant_client, collection_name=collection_name)
    prompt = prompt_flash_card_generation(texts)
    model = ChatGoogleGenerativeAI(model = "gemini-1.5-flash", api_key=api_key)
    response = model.invoke([{"role": "system", "content": "You are an assistant."},
                  {"role": "user", "content": prompt}]
    )
    
    # Parse response
    pattern = r"```json\s*([\s\S]*?)\s*```"
    match = re.search(pattern, response.content)
    if match:
        json_str = match.group(1).strip()
        try:
            return json.loads(json_str)
        except json.JSONDecodeError as e:
            print(f"Error parsing JSON: {e}")
            return None
    else:
        print("No JSON code block found")
        return None

def generate_questions_and_answers_from_video(video: Video, api_key: str) -> list:
    prompt = prompt_flash_card_generation_from_video(video)
    model = ChatGoogleGenerativeAI(model = "gemini-1.5-flash", api_key=api_key)
    response = model.invoke(prompt)
    
    # Parse response
    pattern = r"```json\s*([\s\S]*?)\s*```"
    match = re.search(pattern, response.content)
    if match:
        json_str = match.group(1).strip()
        try:
            return json.loads(json_str)
        except json.JSONDecodeError as e:
            print(f"Error parsing JSON: {e}")
            return None
    else:
        print("No JSON code block found")
        return None

def create_flash_cards_service(api_key: str,
                                collection_name: Optional[str] = None,
                                video: Optional[Video] = None,
                               ) -> flash_cards.FlashCardList:
    if collection_name is not None:
        qa_pairs = generate_questions_and_answers(collection_name, api_key)
    elif video is not None:
        qa_pairs = generate_questions_and_answers_from_video(video, api_key)
    else:
        return {"error": "Either collection_name or video must be provided."}
    flash_card_list = []
    for qa in qa_pairs:
        flash_card = flash_cards.FlashCard(
            question=qa['question'],
            answer=qa['answer'],
        )
        flash_card_list.append(flash_card)

    flash_card_list_obj = flash_cards.FlashCardList(flash_cards=flash_card_list)
    return flash_card_list_obj