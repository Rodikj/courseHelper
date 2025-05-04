from qdrant_client import QdrantClient
from qdrant_client.http.models import VectorParams, Distance
from app.core.config_settings import get_settings
import uuid
from qdrant_client.models import PointStruct
from langchain_google_genai import GoogleGenerativeAIEmbeddings
from unstructured.documents.elements import Element, CompositeElement, Table
from app.models.ingestion_retrieval_models import Texts, Tables, Images
from langchain_core.embeddings.embeddings import Embeddings
from typing import Union

settings = get_settings()

class Ingestion():

    client : QdrantClient = QdrantClient(host=settings.QDRANT_HOST, port=settings.QDRANT_PORT)
    qdrant_collection_name: str = None
    
    def __init__(self,
                 embedding_model: Embeddings,
                 qdrant_collection_name: str,
                 texts: Texts = None,
                 tables: Tables = None,
                 images: Images = None,
                 ):
        
        self.qdrant_collection_name = qdrant_collection_name

        self.ingestion_embedding_model = embedding_model
        self.create_qdrant_collection(qdrant_collection_name)

        self.ingest_texts(texts=texts,
                          collection_name=qdrant_collection_name) if texts else []
        
        self.ingest_tables(tables=tables,
                           collection_name=qdrant_collection_name) if tables else []
        
        self.ingest_images(images=images,
                           collection_name=qdrant_collection_name) if images else []


    def create_qdrant_collection(self, name: str, embedding_size: int = 768):
        self.client.recreate_collection(
            collection_name=name,
            vectors_config=VectorParams(size=embedding_size, distance=Distance.COSINE)  # Match your embedding size
        )

    def create_qdrant_point(self, text_type, summary, full_content, source: str, id: str):
        vector = self.ingestion_embedding_model.embed_query(summary)
        return PointStruct(
            id=id,
            vector=vector,
            payload={
                "doc_type": text_type,
                "summary": summary,
                "full_content": full_content,
                "source": source
            }
        )

    def ingest_texts(self, texts: Texts, collection_name: str):
        text_points = [
            self.create_qdrant_point("text", chunk.summary, chunk.content.text, source = self.qdrant_collection_name, id = chunk.id) 
            for chunk in texts.chunks
        ]

        self.client.upsert(collection_name = collection_name, points = text_points)

    def ingest_tables(self, tables: Tables, collection_name: str):
        table_points = [
            self.create_qdrant_point("table", chunk.summary, chunk.html, source=self.qdrant_collection_name, id = chunk.id) 
            for chunk in tables.chunks
        ]

        self.client.upsert(collection_name=collection_name, points=table_points)
 
    def ingest_images(self, images: Images, collection_name: str):
        image_points = [
            self.create_qdrant_point("image", chunk.summary, chunk.base64, source=self.qdrant_collection_name, id = chunk.id) 
            for chunk in images.chunks
        ]

        self.client.upsert(collection_name=collection_name, points=image_points)