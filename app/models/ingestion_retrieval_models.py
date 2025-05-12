from pydantic import BaseModel, Field, ConfigDict
from unstructured.documents.elements import CompositeElement, Table
from typing import Optional
import uuid
from typing import Any, Protocol

class ChunkContainer(Protocol):
    chunks: list[Any]

class ChunkBase(BaseModel):
    id: str = Field(default_factory=lambda: str(uuid.uuid4()))
    summary: Optional[str] = None

class Summarisations(BaseModel):
    summarisations: Optional[list[str]] = None

class TextChunk(ChunkBase):
    model_config = ConfigDict(arbitrary_types_allowed=True)
    content: CompositeElement = Field(description="Text content")

class TableChunk(ChunkBase):
    model_config = ConfigDict(arbitrary_types_allowed=True)
    content: Table = Field(description="Table content")
    html: Optional[str] = None 

class ImageChunk(ChunkBase):
    base64: str = Field(description="Base64 image content")

class Texts(BaseModel):
    chunks: list[TextChunk]

class Tables(BaseModel):
    chunks: list[TableChunk]

class Images(BaseModel):
    chunks: list[ImageChunk]