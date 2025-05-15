from app.agents.tools.multiply import multiply
from app.agents.tools.vector_retrieval import create_query_vector_tool
from app.agents.tools.docx_vector_retrieval import create_query_vector_tool_for_docx
from app.agents.tools.video_answerer import create_video_answerer_tool
from qdrant_client import QdrantClient
from langchain_google_genai import GoogleGenerativeAIEmbeddings
from langchain_core.tools import Tool
from langchain_core.embeddings import Embeddings
from app.models.video import Video
from langchain_core.language_models.chat_models import BaseChatModel

def get_tools(client: QdrantClient = None,
              embedding_model: Embeddings = None,
              pdf_collection_name: str = None,
              docx_collection_name: str = None,
              video: Video = None,
              llm: BaseChatModel= None
              ) -> list:
    """
    Returns a list of tools available for use in the agent.
    
    This function is used to retrieve the tools that can be utilized by the agent
    for various tasks, such as vector retrieval and mathematical operations.
    
    Returns:
        List[Tool]: A list of tools available for use in the agent.
    """

    tools = []
    
    # If necessary parameters are provided, create a vector retrieval tool
    if client is not None and embedding_model is not None and pdf_collection_name is not None:
        vector_tool = create_query_vector_tool(
            client=client,
            collection_name=pdf_collection_name,
            embedding_model_for_retrieval=embedding_model
        )
        tools.append(vector_tool)
    
    if client is not None and embedding_model is not None and docx_collection_name is not None:
        # Create a query vector tool for DOCX files
        docx_vector_tool = create_query_vector_tool_for_docx(
            client=client,
            collection_name=docx_collection_name,
            embedding_model_for_retrieval=embedding_model
        )
        tools.append(docx_vector_tool)
    
    if video is not None and llm is not None:
        # Create a video answerer tool
        video_tool = create_video_answerer_tool(
            video=video,
            llm=llm
        )
        tools.append(video_tool)
    
    # Always add the multiply tool
    tools.append(multiply)
    
    return tools