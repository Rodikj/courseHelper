# Stage 1: builder
FROM python:3.10-slim AS builder
WORKDIR /app

# Only copy and install requirements first
COPY requirements.txt .
RUN pip install --no-cache-dir --prefix=/install -r requirements.txt

# Stage 2: final image
FROM python:3.10-slim
WORKDIR /app

# Use HTTPS for apt and update DNS for reliability
RUN apt-get update \
    && apt-get install -y --no-install-recommends \
    libreoffice \
    unoconv \
    libgl1 \
    libglib2.0-0 \
    poppler-utils \
    tesseract-ocr \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Set environment variables
ENV REDIS_HOST=redis \
    REDIS_PORT=6379 \
    REDIS_DB=0 \
    QDRANT_HOST=qdrant \
    QDRANT_PORT=6333 \
    GOOGLE_GENAI_UPLOAD_TIMEOUT=1000

# Copy installed Python dependencies
COPY --from=builder /install /usr/local

# Copy application code separately for better caching
COPY ./app ./app
COPY *.py ./
COPY original_files_for_patching/_api_client.py /usr/local/lib/python3.10/site-packages/google/genai/_api_client.py
COPY original_files_for_patching/files.py /usr/local/lib/python3.10/site-packages/google/genai/files.py

# Ensure permissions for the application
RUN chmod -R 755 /app

EXPOSE 8000

# Use shell form CMD for better signal handling
CMD uvicorn main:app --host 0.0.0.0 --port 8000 & celery -A celery_worker worker --loglevel=info