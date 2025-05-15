from app.agents.nodes.conversation_node import Nodes
from app.agents.states.conversation_state import ConversationState
from langgraph.graph import StateGraph, END
from langchain_core.language_models.chat_models import BaseChatModel
from app.agents.tools.tool_config import get_tools
from langchain_core.embeddings import Embeddings
from qdrant_client import QdrantClient
from app.models.video import Video

class ConversationGraph:
    llm: BaseChatModel = None
    graph: StateGraph = None
    tools: list = None
    
    def __init__(self, llm: BaseChatModel,
                 client: QdrantClient = None,
                 embedding_model: Embeddings = None,
                 pdf_collection_name: str = None,
                 docx_collection_name: str = None,
                 system_prompt: str = None,
                 video: Video = None
                 ):
        # Get the current tools and bind them to the LLM
        self.tools = get_tools(client=client,
                               embedding_model=embedding_model,
                               pdf_collection_name=pdf_collection_name,
                               docx_collection_name= docx_collection_name,
                               llm=llm,
                               video=video
                               )
        self.llm = llm.bind_tools(self.tools)
        
        # Create the nodes
        node_obj = Nodes(self.llm, system_prompt, self.tools)
        
        # Build the graph
        graph = StateGraph(ConversationState)
        graph.add_node("llm", node_obj.llm_execute)
        graph.add_node("tool_call", node_obj.tool_calls)
        
        graph.add_conditional_edges(
            "llm",
            node_obj.exists_tool_calling,
            {True: "tool_call", False: END}
        )
        
        graph.add_edge("tool_call", "llm")
        graph.set_entry_point("llm")
        self.graph = graph.compile()