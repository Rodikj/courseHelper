from pytubefix import YouTube
from moviepy import VideoFileClip

def get_youtube_video_duration(video_uri):
    yt = YouTube(video_uri)
    return convert_seconds_to_minutes(yt.length)

def get_file_video_duration(file_path):
    clip = VideoFileClip(file_path)
    return convert_seconds_to_minutes(clip.duration)

def convert_seconds_to_minutes(seconds):
    """Convert seconds into minutes and seconds"""
    minutes = int(seconds // 60)
    sec = int(seconds % 60)
    result = {"minutes": minutes, "seconds": sec}
    return result