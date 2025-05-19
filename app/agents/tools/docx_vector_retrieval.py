from langchain_core.tools import Tool
from app.agents.tools.tool_schemas import docx_query_vector_schema
from qdrant_client import QdrantClient
from langchain_core.embeddings import Embeddings
from typing import Dict, Any

def create_query_vector_tool_for_docx(client: QdrantClient, embedding_model_for_retrieval: Embeddings, collection_name: str):
    """
    Creates a query vector tool with the given client, embedding model, and collection name for DOCX files.
    
    Args:
        client (QdrantClient): The Qdrant client instance.
        embedding_model_for_retrieval (Embeddings): The embedding model for vector retrieval.
        collection_name (str): The name of the collection to query.
    
    Returns:
        Tool: A tool for querying the vector database for DOCX content.
    """
    def _query_vector_database_for_docx(question: str, top_k: int = 5) -> Dict[str, Any]:
        """
        Use this tool to answer any user question about a Word/DOCX file that has been processed and embedded in a vector database.
        Do not ask the user to rephrase their query. Just pass their question as the query argument to this tool and return a helpful response based on the results.

        Given a natural language question, this tool retrieves the most relevant chunks from the vector database using semantic search.

        Args:
            question (str): The user's natural language question.
            top_k (int): The number of top results to return. Default is 5.

        Returns:
            Dict[str, Any]: A list of relevant DOCX chunks that help answer the question.
        """
        if client is None or embedding_model_for_retrieval is None or collection_name is None:
            raise ValueError("Client, embedding model, and collection name must be provided.")
        
        embedded_query = embedding_model_for_retrieval.embed_query(question)
        
        # Perform the query using the provided client and embedding model
        results = client.search(
            collection_name=collection_name,
            query_vector=embedded_query,
            limit=top_k,
            with_payload=True,
        )

        # Process the results
        results_content = []
        for result in results:
            payload = result.payload or {}
            doc_type = payload.get("doc_type")
            if doc_type == "image":
                results_content.append(payload.get("summary"))
            else:
                results_content.append(payload.get("full_content"))

        return {"results": results_content}
    
    return Tool(
        name="search_docx_content",
        description="""
        Use this tool to respond to any user input mentioning a Word or DOCX document, even if they do not explicitly ask a question.
    
        This includes requests to describe, explain, summarize, or provide details about any Word or DOCX content. 
        Trigger this tool whenever the user input contains any reference to a Microsoft Word or DOCX content.

        Args:
            question (str): The user's natural language question.

        Returns:
            Dict[str, Any]: A list of relevant DOCX chunks that help answer the question.
        """,
        func=_query_vector_database_for_docx,
        args_schema=docx_query_vector_schema.QueryDocxVectorSchema
    )
