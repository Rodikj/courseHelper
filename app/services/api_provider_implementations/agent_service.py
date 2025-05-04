from app.agents.graphs.conversation_graph import ConversationGraph
from app.models.request import AIRequest
from app.models.conversation import Message
from langchain_google_genai import ChatGoogleGenerativeAI
from app.utils.conversation_util import convert_conversation_to_agent_format
from langchain_google_genai.embeddings import GoogleGenerativeAIEmbeddings
from qdrant_client import QdrantClient
from app.core.config_settings import get_settings
import os

settings = get_settings()

class AgentService:

    @staticmethod
    def process_agent(request: AIRequest):
        """Handles the agent process based on the selected model provider."""
        # Create a conversation graph
        api_key = request.api_key if request.api_key else os.environ.get("GEMINI_API_KEY")

        model = ChatGoogleGenerativeAI(model=request.model_name, temperature=0.6, max_output_tokens=8192, api_key=api_key)
        embedding_model = GoogleGenerativeAIEmbeddings(model="models/text-embedding-004",
                                          google_api_key=api_key,
                                          task_type="retrieval_query")
        client = QdrantClient(host=settings.QDRANT_HOST, port=settings.QDRANT_PORT)

        graph_obj = ConversationGraph(
            llm=model,
            embedding_model=embedding_model,
            client=client,
            system_prompt=request.system_instruction_text,
            collection_name=request.collection_name
        )

        conversation_list = convert_conversation_to_agent_format(request)
        
        # Execute the graph with the provided request data
        response = graph_obj.graph.invoke(conversation_list)
        ai_response = response['messages'][-1].content

        new_message = Message(role="model", content=ai_response)
        updated_history = request.conversation_history.messages + [new_message]
        
        return {"conversation_history": [msg.model_dump() for msg in updated_history]}