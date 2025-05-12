# app/agents/nodes/conversation_node.py
from langchain_core.language_models.chat_models import BaseChatModel
from langchain_core.messages import ToolMessage, SystemMessage
from app.agents.states.conversation_state import ConversationState
from app.agents.tools.tool_config import get_tools

class Nodes:
    llm: BaseChatModel = None
    system_prompt: str = None
    
    def __init__(self, llm: BaseChatModel, system_prompt: str = None, tools: list = None):
        self.system_prompt = system_prompt
        self.llm = llm
        self.tools = tools 
    
    def llm_execute(self, state: ConversationState) -> ConversationState:
        messages = state['messages']
        
        if self.system_prompt:
            messages = [SystemMessage(content=self.system_prompt)] + messages
        
        # calling LLM
        message = self.llm.invoke(messages)
        
        return {'messages': [message]}
    
    def exists_tool_calling(self, state: ConversationState) -> bool:
        result = state['messages'][-1]
        return len(result.tool_calls) > 0
    
    def tool_calls(self, state: ConversationState) -> ConversationState:

        # Convert to LangChain format
        tool_calls = state['messages'][-1].tool_calls
        print("Tool calls:", tool_calls)
        
        results = []
        tool_dict = {tool.name: tool for tool in self.tools}
        for tool in tool_calls:
            # checking whether tool name is correct
            if not tool["name"] in tool_dict:
                # returning error to the agent
                result = "Error: There's no such tool, please, try again"
            else:
                # getting result from the tool
                tool_obj = tool_dict[tool["name"]]
                result = tool_obj.invoke(tool["args"])
            
            results.append(
                ToolMessage(
                    tool_call_id=tool['id'],
                    name=tool['name'],
                    content=str(result)
                )
            )
        
        # Return the state with the new message added
        return {"messages": results}