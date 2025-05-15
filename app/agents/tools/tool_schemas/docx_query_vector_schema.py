from pydantic import BaseModel, Field

class QueryDocxVectorSchema(BaseModel):
    """
    Schema for the Query Vector Agent.
    """

    # The query to be vectorized
    question: str = Field(..., description="The user's question about the Word / DOCX document content.")