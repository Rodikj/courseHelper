import os
import subprocess
import tempfile

def convert_doc_to_pdf(file_content: bytes, file_type: str = "docx") -> bytes:
    """
    Convert DOC/DOCX bytes to PDF bytes using LibreOffice.
    Preserves all formatting, images, tables, and layout.

    Args:
        file_content: The bytes content of the DOC/DOCX file.
        file_type: The file type ('doc' or 'docx').

    Returns:
        bytes: The PDF file as bytes.
    """
    # Create a temporary directory to work in
    with tempfile.TemporaryDirectory() as temp_dir:
        try:
            # Create temporary input file path
            input_path = os.path.join(temp_dir, f"input.{file_type}")
            output_filename = "output.pdf"
            output_path = os.path.join(temp_dir, output_filename)
            
            # Write the document bytes to the temporary file
            with open(input_path, 'wb') as input_file:
                input_file.write(file_content)

            # Convert using LibreOffice (headless mode)
            cmd = [
                'libreoffice', '--headless', '--convert-to', 'pdf',
                '--outdir', temp_dir, input_path
            ]

            # Run the conversion process
            process = subprocess.run(
                cmd,
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE,
                check=False
            )

            # Check if the output file was created
            if not os.path.exists(output_path):
                # Look for any PDF file in the directory
                pdf_files = [f for f in os.listdir(temp_dir) if f.endswith('.pdf')]
                if not pdf_files:
                    error_msg = process.stderr.decode() if process.stderr else "Unknown error"
                    raise ValueError(f"Conversion failed: {error_msg}")
                output_path = os.path.join(temp_dir, pdf_files[0])

            # Read the PDF file as bytes
            with open(output_path, 'rb') as pdf_file:
                pdf_bytes = pdf_file.read()

            return pdf_bytes

        except Exception as e:
            raise ValueError(f"Error during conversion: {str(e)}")
