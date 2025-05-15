from pydantic import BaseModel, Field
from typing import List, Dict, Any

class FlashCard(BaseModel):
    """
    Schema for the Flash Cards Agent.
    """

    # The question to be answered
    question: str = Field(..., description="The user's question about the PDF content.")

    # The answer to the question
    answer: str = Field(..., description="The answer to the user's question.")

class FlashCardList(BaseModel):
    """
    Schema for a list of Flash Cards.
    """

    # List of flash cards
    flash_cards: list[FlashCard] = Field(..., description="List of flash cards.")

    def add_flash_card(self, FlashCard):
        """
        Add a flash card to the list.
        """
        self.flash_cards.append(FlashCard(**FlashCard))
    
    def to_json_serializable(self) -> List[Dict[str, Any]]:
        """
        Convert the list of flash cards to a JSON-serializable format.
        """
        return [card.model_dump() for card in self.flash_cards]