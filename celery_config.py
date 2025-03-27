from celery import Celery
from app.core.config_settings import get_settings

settings = get_settings()

celery_app = Celery(
    "tasks",
    broker=f"redis://{settings.REDIS_HOST}:{settings.REDIS_PORT}/{settings.REDIS_DB}",
    backend=f"redis://{settings.REDIS_HOST}:{settings.REDIS_PORT}/{settings.REDIS_DB}"
)

celery_app.conf.task_routes = {"celery_worker*": "main-queue"}
celery_app.conf.update(
    task_serializer="json",
    result_serializer="json",
    accept_content=["json"],
    timezone="UTC",
    enable_utc=True,
    worker_time_limit=1800,  # Hard time limit (in seconds)
    worker_soft_time_limit=1500  # Soft time limit (in seconds)
)

celery_app.autodiscover_tasks(["celery_worker"])
