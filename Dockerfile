FROM python:3.10

WORKDIR /app

# Copy and install dependencies
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copy application code
COPY . .

# Replace specific package files with modified versions
COPY original_files_for_patching/_api_client.py /usr/local/lib/python3.10/site-packages/google/genai/_api_client.py
COPY original_files_for_patching/files.py /usr/local/lib/python3.10/site-packages/google/genai/files.py

# Expose FastAPI port
EXPOSE 8000

# Start FastAPI and Celery together
CMD ["sh", "-c", "uvicorn main:app --host 0.0.0.0 --port 8000 & celery -A celery_worker worker --loglevel=info"]
