# The Python Service for "Course Helper"

WARNING: When starting with Docker Compose, reserve at least ~8-9 GB of storage. The app image alone is 7 GB. Meaning, the Docker Compose is gonna take a while to get set up depending on your Internet speed.

The Python service of the "Course Helper" app serves to process requests of GenAI nature. It provides API endpoints using FastAPI, and once an endpoint is requested, each endpoint sends a task to be worked on to a task broker, in this case Celery.

Example usages of the API endpoints can be found in the "example_api_usages.md" file. KEEP IN MIND: right now it only works through Google Studio, so provide an API key from "https://aistudio.google.com/". 

## How to Run the Python Service

#### How to Run it Locally

1. Clone the Python service locally and enter the project folder
2. Create a virtual environment ".venv", and activate it in terminal (Linux: "source .venv/bin/activate", Windows: ".venv\Scripts\activate") (Also Recommended: if using VSCode, press "CTRL+Shift+P" or "Cmd+Shift+P", type in "Python: Select Interpreter", and select the virtual environment)
3. Make sure you install the following packages:
"
apt-get update && apt-get install -y \
    libgl1 \
    libglib2.0-0 \
    poppler-utils \
    tesseract-ocr \
"
4. Install the required packages "pip install -r requirements.txt"
5. OPTIONAL: In the ".venv/lib/python3.10/site-packages/google/genai/" directory, change the files "_api_client.py" and "files.py" with their respective files located in the "original_files_for_patching" directory. This only adds a specific "timeout" when generating URI links through the Gemini service 
6. Start a Redis instance, either by using on Linux:
```
"sudo apt install redis", and then "sudo systemctl start redis-server"
```
or alternatively start a Redis instance as a Docker container:
```
"docker run -d --name redis-server -p 6379:6379 redis:alpine"
```
7. Start the Celery task broker by running "celery -A celery_worker worker --loglevel=info --hostname=workerName"
8. Start the FastAPI app by running "uvicorn main:app --reload"

#### How to Run it Using Docker Compose

1. Clone the Python service locally and enter the project folder
2. Type in "docker compose up"

The Swagger UI of the FastAPI app can be accessed through "localhost:8000/docs".

## Project Structure Explained

First, we have "main.py", that's where we initialize the FastAPI app. We include a router for the API endpoints that can be found in "app.api.routes". The routes can take a Pydantic model which defines what kind of JSON data payload the endpoint accepts. These models can be found in "app.models"

Then we have "celery_worker", that's where we initialize the Celery app with configuration "celery_config". In "celery_worker", we define the tasks we want Celery to create and run.

"app.api.routes" calls upon the "celery_worker" tasks. Then Celery creates a task that finishes some kind of functionality, or more specifically a "celery_worker" task calls upon functionalities set in "app.services".

"app.agents" now holds LangGraph agentic approach.

"app.utils" just holds helper functions.