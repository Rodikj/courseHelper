# Stage 1: builder
FROM python:3.10-slim AS builder
WORKDIR /app

# Only copy and install requirements first
COPY requirements.txt .
RUN pip install --no-cache-dir --prefix=/install -r requirements.txt

# Stage 2: final image
FROM python:3.10-slim
WORKDIR /app

# Install system deps for OpenCV
RUN apt-get update && apt-get install -y \
    libgl1 \
    libglib2.0-0 \
    poppler-utils \
    tesseract-ocr \
    && rm -rf /var/lib/apt/lists/*

# Set env vars
RUN echo "REDIS_HOST=redis\nREDIS_PORT=6379\nREDIS_DB=0\nQDRANT_HOST=qdrant\nQDRANT_PORT=6333" > /app/.env

# Copy installed Python deps
COPY --from=builder /install /usr/local

# 🔁 Now copy application code separately
COPY ./app ./app
COPY *.py ./
COPY original_files_for_patching/_api_client.py /usr/local/lib/python3.10/site-packages/google/genai/_api_client.py
COPY original_files_for_patching/files.py /usr/local/lib/python3.10/site-packages/google/genai/files.py

EXPOSE 8000
CMD ["sh", "-c", "uvicorn main:app --host 0.0.0.0 --port 8000 & celery -A celery_worker worker --loglevel=info"]
