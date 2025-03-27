from fastapi import FastAPI
from celery.result import AsyncResult
from app.models.request import AIRequest
from celery_worker import process_ai_request_task  # Import the registered task, not the function
from app.api import routes
from app.core.config_settings import get_settings

# Get application settings
settings = get_settings()

app = FastAPI(
    title=settings.APP_NAME,
    version=settings.APP_VERSION,
    debug=settings.DEBUG
)

# Include API router
app.include_router(routes.router, prefix=settings.API_PREFIX)
