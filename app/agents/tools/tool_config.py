from app.agents.tools.multiply import multiply
from app.agents.tools.vector_retrieval import create_query_vector_tool
from qdrant_client import QdrantClient
from langchain_google_genai import GoogleGenerativeAIEmbeddings
from langchain_core.tools import Tool
from langchain_core.embeddings import Embeddings

def get_tools(client: QdrantClient = None, embedding_model: Embeddings = None, collection_name: str = None) -> list:
    """
    Returns a list of tools available for use in the agent.
    
    This function is used to retrieve the tools that can be utilized by the agent
    for various tasks, such as vector retrieval and mathematical operations.
    
    Returns:
        List[Tool]: A list of tools available for use in the agent.
    """

    tools = []
    
    # If necessary parameters are provided, create a vector retrieval tool
    if client is not None and embedding_model is not None and collection_name is not None:
        vector_tool = create_query_vector_tool(
            client=client,
            collection_name=collection_name,
            embedding_model_for_retrieval=embedding_model
        )
        tools.append(vector_tool)
    
    # Always add the multiply tool
    tools.append(multiply)
    
    return tools