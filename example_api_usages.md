## /api/process_ai

#### Example cURL to "/api/process_ai":

This takes an object "conversation_history", which is a list of "messages". The user question/prompt should be inserted as the last message within this list,
for which the AI will return an answer for.

"
curl -X 'POST' \
  'http://localhost:8000/api/process-ai' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "model_provider": "gemini",
  "model_name": "gemini-1.5-flash",
  "system_instruction_text": "You are a helpful AI.",
  "conversation_history": {
    "messages": [
      {
        "content": "Who are you?",
        "role": "user"
      },
      {
        "role": "model",
        "content": "I am a large language model, trained by Google. I'\''m a computer program designed to process information and respond to a wide range of prompts and questions in a human-like way. I don'\''t have a name, personal experiences, or feelings. My purpose is to help users by providing information, generating text, translating languages, and more."
      },
      {
        "content": "And what can you do?",
        "role": "user"
      },
      {
        "role": "model",
        "content": "I can do a lot of things! My abilities include:\n\n- **Generating text:** I can write stories, poems, articles, summaries, emails, letters, etc., in various styles and tones. I can also translate languages.\n\n- **Answering questions:** I can access and process information from a massive dataset to answer your questions factually and comprehensively.\n\n- **Summarizing text:** I can condense large amounts of text into concise summaries.\n\n- **Translating languages:** I can translate text between many different languages.\n\n- **Writing different creative text formats:** I can write code, scripts, musical pieces, emails, letters, etc.\n\n- **Following instructions:** I can follow your instructions and complete tasks as directed, within the bounds of my capabilities and ethical guidelines.\n\n- **Providing information:** I can provide information on a vast range of topics.\n\nHowever, it'\''s important to note that:\n\n- **I am not omniscient:** My knowledge is based on the data I was trained on, which has a cutoff point.\n\n- **I am not sentient:** I don'\''t have feelings, opinions, or beliefs.\n\n- **I cannot provide advice on sensitive topics:** I cannot give medical, legal, or financial advice."
      },
      {
        "content": "Can you describe this video?",
        "role": "user",
        "video": {
          "type": "YOUTUBE_VIDEO",
          "uri": "https://www.youtube.com/watch?v=fWjsdhR3z3c"
        }
      }
    ]
  },
  "api_key": "00000000000000"
}'
"

#### Response Body for "/api/process_ai":

"
{
  "task_id": "78c3d088-1493-46da-b99a-1eb80920f599",
  "status": "PENDING"
}
"

#### Response Body from "api/task_status/{task_id}" for "/api/process_ai":

As you can see here, the same object as the request is sent back, with the modification where the last message this time is the one from the AI model with role "model".

"
{
  "task_id": "78c3d088-1493-46da-b99a-1eb80920f599",
  "status": "SUCCESS",
  "result": {
    "conversation_history": [
      {
        "role": "user",
        "content": "Who are you?",
        "video": null
      },
      {
        "role": "model",
        "content": "I am a large language model, trained by Google. I'm a computer program designed to process information and respond to a wide range of prompts and questions in a human-like way. I don't have a name, personal experiences, or feelings. My purpose is to help users by providing information, generating text, translating languages, and more.",
        "video": null
      },
      {
        "role": "user",
        "content": "And what can you do?",
        "video": null
      },
      {
        "role": "model",
        "content": "I can do a lot of things! My abilities include:\n\n- **Generating text:** I can write stories, poems, articles, summaries, emails, letters, etc., in various styles and tones. I can also translate languages.\n\n- **Answering questions:** I can access and process information from a massive dataset to answer your questions factually and comprehensively.\n\n- **Summarizing text:** I can condense large amounts of text into concise summaries.\n\n- **Translating languages:** I can translate text between many different languages.\n\n- **Writing different creative text formats:** I can write code, scripts, musical pieces, emails, letters, etc.\n\n- **Following instructions:** I can follow your instructions and complete tasks as directed, within the bounds of my capabilities and ethical guidelines.\n\n- **Providing information:** I can provide information on a vast range of topics.\n\nHowever, it's important to note that:\n\n- **I am not omniscient:** My knowledge is based on the data I was trained on, which has a cutoff point.\n\n- **I am not sentient:** I don't have feelings, opinions, or beliefs.\n\n- **I cannot provide advice on sensitive topics:** I cannot give medical, legal, or financial advice.",
        "video": null
      },
      {
        "role": "user",
        "content": "Can you describe this video?",
        "video": {
          "type": "YOUTUBE_VIDEO",
          "uri": "https://www.youtube.com/watch?v=fWjsdhR3z3c",
          "path": null,
          "duration": {
            "minutes": 10,
            "seconds": 30
          }
        }
      },
      {
        "role": "model",
        "content": "Certainly! Here is a description of the video:\n\nThis video is a tutorial on getting started with Python in under ten minutes. The YouTuber uses Python 3.8 and PyCharm as a code editor.  The tutorial covers several key aspects of Python:\n\n1. **Assigning Values to Variables:** The video demonstrates how to create variables and assign string values to them, highlighting Python's case-sensitive nature and naming conventions (using underscores for multi-word variables).\n\n2. **Data Types:** It introduces fundamental data types: integers (numbers), strings (text), and booleans (True/False).\n\n3. **Printing Values:** The video shows how to print the values of variables using the `print()` function, including combining strings and converting integers to strings for concatenation.\n\n4. **Math Operations:** Basic arithmetic operations (+, -, \\*, /, \\*\\*) are explained and demonstrated.\n\n5. **Logic Statements (`if`, `elif`, `else`):**  Conditional logic is shown using `if`, `elif` (else if), and `else` statements to execute different code blocks based on conditions.\n\n6. **For Loops:** The `for` loop is introduced, demonstrating iteration through a range of numbers and a list of strings.\n\n7. **While Loops:** The `while` loop is explained, showing how to create an infinite loop (`while True`) and use the `break` statement to control the loop's termination.  User input is incorporated to demonstrate loop control.\n\n8. **Functions:** The video explains how to define functions using the `def` keyword and how to call them. The `pass` keyword is introduced as a placeholder for function bodies when the code isn't yet implemented.\n\n9. **Try and Except Blocks:** The `try` and `except` blocks are demonstrated to handle potential errors (exceptions) during code execution.  This is shown with user input where the program attempts to convert the input to an integer.  If the input is not a valid number, the `except` block catches the error and prints an appropriate message.\n\nThe tutorial provides concise explanations and practical examples to help beginners grasp the basics of Python programming.  Each concept is illustrated with short code snippets, and the results of running the code are shown in the video.\n",
        "video": null
      }
    ]
  }
}
"

## /api/upload

#### Example cURL to "/api/upload":

"
curl -X 'POST' \
  'http://localhost:8000/api/upload/' \
  -H 'accept: application/json' \
  -H 'Content-Type: multipart/form-data' \
  -F 'file=@C:\Users\YourName\Videos\filename.webm;type=video/webm'
",

cURL on Windows CMD:

"
curl -X POST ^
  "http://localhost:8000/api/upload/" ^
  -H "accept: application/json" ^
  -H "Content-Type: multipart/form-data" ^
  -F "file=@C:\\Users\\tomce\\Downloads\\Learn Python in Less than 10 Minutes for Beginners (Fast & Easy).mp4;type=video/mp4"
"

#### Response Body for "/api/upload":

"
{
  "filename": "Learn Python in Less than 10 Minutes for Beginners (Fast & Easy).mp4",
  "file_id": "53509900-785e-4fea-bfda-789c993ae194",
  "task_id": "55e5ce8d-465b-4efc-b9f8-fef232a0f145"
}
"

#### Response Body from "/api/task-status/{task_id}" for "/api/upload":

This provides the path to the uploaded file within the project.

"
{
  "task_id": "55e5ce8d-465b-4efc-b9f8-fef232a0f145",
  "status": "SUCCESS",
  "result": {
    "filename": "Learn Python in Less than 10 Minutes for Beginners (Fast & Easy).mp4",
    "file_id": "53509900-785e-4fea-bfda-789c993ae194",
    "path": "/home/srate/fakultet/ikt_my_try/uploads/53509900-785e-4fea-bfda-789c993ae194_Learn Python in Less than 10 Minutes for Beginners (Fast & Easy).mp4"
  }
}
"

## /api/get_video_uri

#### Example cURL to "/api/get_video_uri":

The path should be the path to the uploaded video to the project from "/api/upload"

"
curl -X 'POST' \
  'http://localhost:8000/api/get_video_uri' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "video": {
    "type": "FILE_VIDEO",
    "path": "/home/srate/fakultet/ikt_my_try/uploads/f88354f1-d7e3-45e1-af2c-c124e6eb1830_0963bd95-5a42-4de5-90b9-ebaf4cba64de.webm"
  },
  "uri_provider": "gemini_uri_provider",
  "api_key": "0000000000000000"
}
"

#### Response Body for "/api/get_video_uri":

"
{
  "task_id": "d761eb4c-ffdf-423c-8397-cdc570fb1513",
  "status": "PENDING"
}
"

#### Response Body from "/api/task_status/{task_id}" for "/api/get_video_uri":

For future implementations: 'Duration' might be 'None'.

"
{
  "task_id": "410e0de8-bbba-446a-ac7a-7ee2790c77b4",
  "status": "SUCCESS",
  "result": {
    "video": {
      "type": "FILE_VIDEO",
      "uri": "https://generativelanguage.googleapis.com/v1beta/files/8z72unjdpsxa",
      "uri_data": {
        "uri_file_name": "files/8z72unjdpsxa",
        "uri_state": "ACTIVE",
        "uri_mime_type": "video/webm"
      },
      "path": "/home/srate/fakultet/ikt_my_try/uploads/3bfbc962-c638-4986-8400-0b9e727954ca_0963bd95-5a42-4de5-90b9-ebaf4cba64de.webm",
      "duration": {
        "minutes": 1,
        "seconds": 49
      }
    },
    "uri_provider": "gemini_uri_provider",
    "api_key": "0000000000000000000000"
  }
}
"

This Video object can then be passed to a user conversation in a conversation history in "/api/process-ai":

"
curl -X 'POST' \
  'http://localhost:8000/api/process-ai' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "model_provider": "gemini",
  "model_name": "gemini-1.5-flash",
  "system_instruction_text": "You are a helpful AI that explains educational content.",
  "conversation_history": {
    "messages": [
      {
        "content": "Who are you?",
        "role": "user"
      },
      {
        "role": "model",
        "content": "I am a large language model, trained by Google. I'\''m a computer program designed to process information and respond to a wide range of prompts and questions in a human-like way. I don'\''t have a name, personal experiences, or feelings. My purpose is to help users by providing information, generating text, translating languages, and more."
      },
      {
        "content": "And what can you do?",
        "role": "user"
      },
      {
        "role": "model",
        "content": "I can do a lot of things! My abilities include:\n\n- **Generating text:** I can write stories, poems, articles, summaries, emails, letters, etc., in various styles and tones. I can also translate languages.\n\n- **Answering questions:** I can access and process information from a massive dataset to answer your questions factually and comprehensively.\n\n- **Summarizing text:** I can condense large amounts of text into concise summaries.\n\n- **Translating languages:** I can translate text between many different languages.\n\n- **Writing different creative text formats:** I can write code, scripts, musical pieces, emails, letters, etc.\n\n- **Following instructions:** I can follow your instructions and complete tasks as directed, within the bounds of my capabilities and ethical guidelines.\n\n- **Providing information:** I can provide information on a vast range of topics.\n\nHowever, it'\''s important to note that:\n\n- **I am not omniscient:** My knowledge is based on the data I was trained on, which has a cutoff point.\n\n- **I am not sentient:** I don'\''t have feelings, opinions, or beliefs.\n\n- **I cannot provide advice on sensitive topics:** I cannot give medical, legal, or financial advice."
      },
      {
        "content": "Can you describe this video?",
        "role": "user",
        "video": {
          "type": "FILE_VIDEO",
          "uri": "https://generativelanguage.googleapis.com/v1beta/files/8z72unjdpsxa",
          "uri_data": {
            "uri_file_name": "files/8z72unjdpsxa",
            "uri_state": "ACTIVE",
            "uri_mime_type": "video/webm"
          },
          "path": "/home/srate/fakultet/ikt_my_try/uploads/3bfbc962-c638-4986-8400-0b9e727954ca_0963bd95-5a42-4de5-90b9-ebaf4cba64de.webm",
          "duration": {
            "minutes": 1,
            "seconds": 49
          }
        }
      }
    ]
  },
  "api_key": "000000000000000000000000000"
}
'
"