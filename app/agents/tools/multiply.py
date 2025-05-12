from langchain_core.tools import tool
from app.agents.tools.tool_schemas import multiply_schema

@tool(args_schema=multiply_schema.MultiplySchema)
def multiply(number1: float, number2: float) -> float:
    """
    Multiplies two numbers together.
    
    Args:
        number1 (float): The first number to multiply.
        number2 (float): The second number to multiply.

    Returns:
        float: The product of the two numbers.
    """
    return number1 * number2