from unstructured.partition.pdf import partition_pdf
from unstructured.partition.docx import partition_docx
from unstructured.documents.elements import Element, CompositeElement, Table
from typing import Union
from app.models.enums import FileTypeFastAPI
from app.models.ingestion_retrieval_models import Texts, Tables, Images, TextChunk, TableChunk, ImageChunk
import io
from app.utils.convert_docx_to_pdf_util import convert_doc_to_pdf

def parse_pdf(file_path: str = None, file_content: bytes = None, file_type: str = None) -> list[Element]:
    if file_path == None and file_content==None:
        raise Exception("You must provide either a file path or file content!")
    
    if file_type == FileTypeFastAPI.WORD:
        file_content = convert_doc_to_pdf(file_content, file_type="docx")

    chunks = partition_pdf(
        file_path= file_path,
        file = io.BytesIO(file_content),
        infer_table_structure=True,            # extract tables
        strategy="hi_res",                     # mandatory to infer tables
        table_extraction_mode="bordered",

        extract_image_block_types=["Image", "Table"],   # Add 'Table' to list to extract image of tables
        # image_output_dir_path=output_path,   # if None, images and tables will saved in base64

        extract_image_block_to_payload=True,   # if true, will extract base64 for API usage

        chunking_strategy="by_title",          # or 'basic'
        max_characters=10000,                  # defaults to 500
        combine_text_under_n_chars=2000,       # defaults to 0
        new_after_n_chars=6000,

        # extract_images_in_pdf=True,          # deprecated
    )
    return chunks

def get_texts_elements(chunks: list[Element]) -> Texts:
    text_chunks = [
        TextChunk(content=chunk)
        for chunk in chunks
        if isinstance(chunk, CompositeElement)
    ]
    return Texts(chunks=text_chunks)

def get_image_elements_base64(chunks: list[Element]) -> Images:
    image_chunks = []
    for chunk in chunks:
        if isinstance(chunk, CompositeElement):
            for el in chunk.metadata.orig_elements:
                if el.__class__.__name__ == "Image":
                    image_base64 = getattr(el.metadata, "image_base64", None)
                    if image_base64:
                        image_chunks.append(ImageChunk(base64=image_base64))

    return Images(chunks=image_chunks)

def get_table_elements(chunks: list[Element]) -> Tables:
    table_chunks = []

    for chunk in chunks:
        if isinstance(chunk, CompositeElement):
            for el in chunk.metadata.orig_elements:
                if isinstance(el, Table):
                    table_chunks.append(
                        TableChunk(content=el, html=getattr(el.metadata, "text_as_html", None))
                    )

    return Tables(chunks=table_chunks)