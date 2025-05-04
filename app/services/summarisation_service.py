from langchain_google_genai import ChatGoogleGenerativeAI
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.output_parsers import StrOutputParser
from app.prompts import prompt_summarisation_text_table, prompt_summarisation_image
from unstructured.documents.elements import Element, CompositeElement, Table
from langchain_core.language_models.chat_models import BaseChatModel
from app.models.ingestion_retrieval_models import Texts, Tables, Images, ChunkContainer
from typing import Union, List
from typing import Callable, Sequence, Protocol, Any
import time
import math

class Summarisation():
    max_concurrency: int = 1
    model: BaseChatModel = None
    
    prompt_text_summarisation: ChatPromptTemplate = None
    prompt_image_summarisation: ChatPromptTemplate = None
    
    summarize_texts_tables_chain: dict = None
    summarize_images_chain: dict = None
    
    def __init__(self,
                model: BaseChatModel,
                max_concurrency: int = 1):
                
        self.max_concurrency = max_concurrency
        self.model = model
        
        self.prompt_text_summarisation = ChatPromptTemplate.from_template(prompt_summarisation_text_table.prompt_text_summarisation)
        self.prompt_image_summarisation = ChatPromptTemplate.from_messages(prompt_summarisation_image.prompt_image_summarisation)
        
        self.summarize_texts_tables_chain = {"element": lambda x: x} | self.prompt_text_summarisation | model | StrOutputParser()
        self.summarize_images_chain = self.prompt_image_summarisation | model | StrOutputParser()
                    
    def rate_limited_batch(self, items, chain, max_retries=5):
        """Process items in batches with exception-based rate limiting"""
        if not items:
            return []
        
        results = []
        batch_params = {"max_concurrency": self.max_concurrency}
        
        # Process all items at once and handle rate limiting reactively
        remaining_items = items
        while remaining_items:
            try:
                # Try to process all remaining items
                batch_results = chain.batch(remaining_items, batch_params)
                results.extend(batch_results)
                remaining_items = []  # All processed successfully
                
            except Exception as e:
                # Check if it's a rate limit error
                if "ResourceExhausted: 429" in str(e) or "You exceeded your current quota" in str(e):
                    print("Rate limit reached. Waiting 60 seconds before retrying...")
                    time.sleep(60)
                    # Continue with retry in the next loop iteration
                else:
                    # For other exceptions, re-raise
                    raise
        
        return results
    
    def summarise_chunks(self,
                      container: ChunkContainer,
                      extract_input: Callable[[Any], Any],
                      summarization_chain: Any):
        
        if not container or not container.chunks:
            return
            
        inputs = [extract_input(chunk) for chunk in container.chunks]
        summaries = self.rate_limited_batch(inputs, summarization_chain)
        
        for chunk, summary in zip(container.chunks, summaries):
            chunk.summary = summary
        
        return container
            
    # def summarise_texts(self) -> list[str]:
    #     if not self.texts or not self.texts.chunks:
    #         return []

    #     summaries = self.rate_limited_batch(self.texts.chunks, self.summarize_texts_tables_chain)

    #     for chunk, summary in zip(self.texts.chunks, summaries):
    #         chunk.summary = summary

    #     return summaries
    
    # def summarise_tables(self, tables: Tables) -> list[str]:
    #     if not tables or not tables.chunks:
    #         return []

    #     html_chunks = [chunk.html for chunk in tables.chunks if chunk.html]
    #     summaries = self.rate_limited_batch(html_chunks, self.summarize_texts_tables_chain)

    #     for chunk, summary in zip(tables.chunks, summaries):
    #         chunk.summary = summary

    #     return summaries
    
    # def summarise_images(self, images: Images) -> list[str]:
    #     if not images or not images.chunks:
    #         return []

    #     base64_chunks = [chunk.base64 for chunk in images.chunks if chunk.base64]
    #     summaries = self.rate_limited_batch(base64_chunks, self.summarize_images_chain)

    #     for chunk, summary in zip(images.chunks, summaries):
    #         chunk.summary = summary

    #     return summaries