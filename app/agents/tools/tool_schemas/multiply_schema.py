from pydantic import BaseModel, Field

class MultiplySchema(BaseModel):
    """
    Schema for the Multiply Agent.
    """

    # The first number to multiply
    number1: float = Field(..., description="The first number to multiply")

    # The second number to multiply
    number2: float = Field(..., description="The second number to multiply")