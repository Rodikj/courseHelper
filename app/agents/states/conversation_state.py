from app.models.conversation import Message, ConversationHistory 
from app.models.request import AIRequest
from typing import Annotated, TypedDict
from langchain_core.messages import AnyMessage
import operator

class ConversationState(TypedDict):
     messages: Annotated[list[AnyMessage], operator.add]