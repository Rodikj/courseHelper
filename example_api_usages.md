## Endpoint: /api/task-status/{task_id}

This app uses Celery tasks to manage the processes. So almost all of these API endpoints return task IDs.

You pass these task IDs to this endpoint to check whether a process has been finished ("SUCCESS"), threw an error ("FAILURE"), or is still pending ("PENDING"). If the task was successfull, you will get the proper response body here, which will be explained in the other endpoints below depending on which endpoint you're using.

##### **Example usage**:
```
curl -X 'GET' \
  'http://localhost:8000/api/task-status/0f3a74f0-0f04-4ac4-bc03-51bac9177c93' \
  -H 'accept: application/json'
```

<<<<<<< HEAD
<<<<<<< HEAD
In the main object, you can now also specify a "collection_name" parameter. Collection name can be recieved by uploading a PDF file, which after the processing finishes (4-5 minutes), the task will return a collection name. Afterwards, when a user specifies a question about the PDF in the conversation history, an answer will be provided by doing a similarity search for the collection name, which is the PDF file chunked and summarised, in the Qdrant vector database.

"
curl -X 'POST' \
  'http://localhost:8000/api/process-ai' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "model_provider": "gemini",
  "model_name": "gemini-1.5-flash",
  "system_instruction_text": "You are a helpful AI.",
  "collection_name": "e6f135f4-3a4a-47b1-85b9-a8bcc9f2e577",
  "conversation_history": {
    "messages": [
      {
        "content": "Who are you?",
        "role": "user"
      },
      {
        "role": "model",
        "content": "I am a large language model, trained by Google. I'm a computer program designed to process information and respond to a wide range of prompts and questions in a human-like way. I don't have a name, personal experiences, or feelings. My purpose is to help users by providing information, generating text, translating languages, and more."
      },
      {
        "content": "And what can you do?",
        "role": "user"
      },
      {
        "role": "model",
        "content": "I can do a lot of things! My abilities include:\n\n- **Generating text:** I can write stories, poems, articles, summaries, emails, letters, etc., in various styles and tones. I can also translate languages.\n\n- **Answering questions:** I can access and process information from a massive dataset to answer your questions factually and comprehensively.\n\n- **Summarizing text:** I can condense large amounts of text into concise summaries.\n\n- **Translating languages:** I can translate text between many different languages.\n\n- **Writing different creative text formats:** I can write code, scripts, musical pieces, emails, letters, etc.\n\n- **Following instructions:** I can follow your instructions and complete tasks as directed, within the bounds of my capabilities and ethical guidelines.\n\n- **Providing information:** I can provide information on a vast range of topics.\n\nHowever, it's important to note that:\n\n- **I am not omniscient:** My knowledge is based on the data I was trained on, which has a cutoff point.\n\n- **I am not sentient:** I don't have feelings, opinions, or beliefs.\n\n- **I cannot provide advice on sensitive topics:** I cannot give medical, legal, or financial advice."
      },
      {
        "content": "In the PDF, explain to me How does positional encoding help the model understand the order of tokens in sequences, and what alternatives could be used?",
        "role": "user"
      }
    ]
  },
  "api_key": "00000000000000000"
}
"

=======
>>>>>>> 143d6af (Initial Python service commit)
#### Response Body for "/api/process_ai":

"
=======
##### **Response Body for PENDING**:
```
>>>>>>> 933d357 (Added DOCX and Flash Cards implementations)
{
  "task_id": "0f3a74f0-0f04-4ac4-bc03-51bac9177c93",
  "status": "PENDING",
  "result": null
}
```

## Endpoint: /api/upload

#### Uploading a PDF/DOCX File

The first use of this endpoint is that you can upload a PDF or DOCX file. When you upload these types of files, it is absolutely CRUCIAL to provide an API key (specifically a Google Studio API key).

<<<<<<< HEAD
<<<<<<< HEAD
Example response when a collection name is specified (don't look at 'tool_calls" and 'tool_results', although tool calls are used they're not implemented properly in conversation history yet):
=======
What it will do is process the file into a Qdrant vector database collection. Then it will return a JSON object containing a "qdrant_collection_name" and "flash_cards" that's a list of "question"/"answer" pairs.
>>>>>>> 933d357 (Added DOCX and Flash Cards implementations)

The qdrant_collection_name will be later used in the '/api/process-ai' endpoint to ask questions based on the PDF/DOCX file by specifying 'pdf_collection_name' or 'docx_collection_name' in the request (refer to that endpoint in this documentation below for a better description)

<<<<<<< HEAD
=======
>>>>>>> 143d6af (Initial Python service commit)
## /api/upload

#### Example cURL to "/api/upload":

<<<<<<< HEAD
If a PDF file is uploaded, now an API key will be needed. For now, it only works with a Google Studio API key. The API key is needed because we are using an embedding model and performing summarisations using Google Studio. KEEP IN MIND: Depending on the size of the PDF file, processing could take up to 4-5 minutes depending on how strong your PC/Laptop is!

Example CURL for PDF file:

"
=======
##### **Example usage for PDF file**:
```
>>>>>>> 933d357 (Added DOCX and Flash Cards implementations)
curl -X 'POST' \
  'http://localhost:8000/api/upload/?api_key=<PROVIDE_API_KEY_HERE>' \
  -H 'accept: application/json' \
  -H 'Content-Type: multipart/form-data' \
  -F 'video=string' \
  -F 'file=@attention.pdf;type=application/pdf'
```

<<<<<<< HEAD
Other example cURLs:

=======
>>>>>>> 143d6af (Initial Python service commit)
"
=======
##### **Example usage for DOCX file**:
```
>>>>>>> 933d357 (Added DOCX and Flash Cards implementations)
curl -X 'POST' \
  'http://localhost:8000/api/upload/?api_key=<PROVIDE_API_KEY_HERE>' \
  -H 'accept: application/json' \
  -H 'Content-Type: multipart/form-data' \
<<<<<<< HEAD
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

<<<<<<< HEAD
Response body if it's a PDF file:
=======
  -F 'file=@attention.docx;type=application/vnd.openxmlformats-officedocument.wordprocessingml.document'
```
>>>>>>> 933d357 (Added DOCX and Flash Cards implementations)

##### **Response Body**:
```
{
  "filename": "attention.pdf",
  "file_id": "29c0127f-6620-466d-967f-adabdda7597a",
  "task_id": "2e6c0ecc-c681-4eca-8d51-c35db04c6aed",
  "parse_summarise_ingest_task_id": "9c55e947-a2c4-4057-bca6-b08549477ca8"
}
```

<<<<<<< HEAD
=======
>>>>>>> 143d6af (Initial Python service commit)
#### Response Body from "/api/task-status/{task_id}" for "/api/upload":

This provides the path to the uploaded file within the project.

"
=======
##### **Response Body from a successfull 'parse_summarise_ingest_task_id' task completed in 'api/task-status/{task_id}'**:
```
>>>>>>> 933d357 (Added DOCX and Flash Cards implementations)
{
  "task_id": "9c55e947-a2c4-4057-bca6-b08549477ca8",
  "status": "SUCCESS",
  "result": {
    "qdrant_collection_name": "29c0127f-6620-466d-967f-adabdda7597a",
    "flash_cards": [
      {
        "question": "What is the core innovation of the Transformer model, and how does it differ from previous sequence transduction models?",
        "answer": "The Transformer's core innovation is its reliance solely on the attention mechanism, replacing recurrent or convolutional layers commonly used in encoder-decoder architectures. This allows for significantly faster training and improved performance on translation tasks."
      },
      {
        "question": "How does the Transformer handle the sequential nature of input data, given its lack of recurrence or convolution?",
        "answer": "The Transformer incorporates positional encodings into the input embeddings.  These encodings, using sine and cosine functions of different frequencies, provide information about the relative or absolute position of tokens in the sequence, allowing the model to understand word order."
      },
      {
        "question": "What are the key components of the Transformer's encoder and decoder stacks?",
        "answer": "Both the encoder and decoder consist of stacks of identical layers. Each layer includes a multi-head self-attention mechanism, a position-wise fully connected feed-forward network, and residual connections with layer normalization. The decoder additionally includes a multi-head attention layer over the encoder's output."
      },
      {
        "question": "Explain the 'Scaled Dot-Product Attention' mechanism and why the scaling factor (1/√dk) is crucial.",
        "answer": "Scaled Dot-Product Attention computes a weighted sum of values based on the dot products of queries and keys, scaled by 1/√dk. This scaling is essential to prevent the dot products from becoming too large for large dk values, which would lead to extremely small gradients in the softmax function and hinder learning."
      },
      {
        "question": "What future research directions are mentioned for the Transformer architecture?",
        "answer": "Future research plans include applying the Transformer to modalities beyond text (images, audio, video), investigating local or restricted attention mechanisms for efficiency with large inputs/outputs, and exploring ways to make generation less sequential."
      }
    ]
  }
}
```

#### Getting Flash Cards for Video Ojbect

Although there's a dedicated endpoint for creating flash cards, you can also get flash cards for VIDEOS in the '/api/upload' endpoint here. So use whichever way you prefer.

BUT KEEP IN MIND - this way we do NOT create a dedicated task for it, the endpoint will directly return the JSON object. Also API KEY IS REQUIRED HERE.

##### **Example usage for getting VIDEO flash cards**:
```
curl -X 'POST' \
  'http://localhost:8000/api/upload/?api_key=<YOUR_API_KEY_HERE>' \
  -H 'accept: application/json' \
  -H 'Content-Type: multipart/form-data' \
  -F 'video={"type":"YOUTUBE_VIDEO","uri":"https://www.youtube.com/watch?v=fWjsdhR3z3c"}'
```

##### **Response Body**:
```
{
  "video": {
    "type": "YOUTUBE_VIDEO",
    "uri": "https://www.youtube.com/watch?v=fWjsdhR3z3c",
    "uri_data": null,
    "path": null,
    "duration": null
  },
  "flash_cards": [
    {
      "question": "What is the purpose of the `pass` keyword in Python?",
      "answer": "The `pass` keyword is used as a placeholder where syntactically some code is required, but you don't want any commands or actions to be executed.  It's useful for creating function or class skeletons that can be fleshed out later."
    },
    {
      "question": "How does the `try...except` block handle exceptions in Python?",
      "answer": "The `try` block contains code that might raise an exception. If an exception occurs, the code within the `except` block is executed, preventing the program from crashing. This allows for graceful error handling."
    },
    {
      "question": "What is the difference between `item` and `Item` in Python?",
      "answer": "Python is case-sensitive.  `item` and `Item` are treated as two completely different variables. This distinction is crucial when defining and using variables."
    },
    {
      "question": "How are mathematical operations performed in Python?",
      "answer": "Python uses standard mathematical symbols: `+` for addition, `-` for subtraction, `*` for multiplication, `/` for division, and `**` for exponentiation. The order of operations follows standard mathematical rules."
    },
    {
      "question": "Explain the functionality of a `for` loop in Python when iterating through a range.",
      "answer": "A `for` loop with `range(n)` iterates `n` times.  Importantly, the index starts at 0 and goes up to `n-1`. If you need the loop to start at 1, you must manually add 1 to the index variable within the loop's body."
    }
  ]
}
```

#### Uploading a Video File

Another thing we can do with this endpoint is upload a video. When uploading a video, we do NOT need to provide an API key. This will return a file path of the video, which we can later use in the '/api/get_video_uri' endpoint.

That endpoint will return a video object with an URI link that can be later used to chat at the '/api/process-ai' endpoint that will be explained later in this documentation.

##### **Other Video Example Usage**:
```
curl -X 'POST'
'http://localhost:8000/api/upload/'
-H 'accept: application/json'
-H 'Content-Type: multipart/form-data'
-F 'file=@C:\Users\YourName\Videos\filename.webm;type=video/webm'
```

##### **Example Usage on Windows**:
```
curl -X POST ^ "http://localhost:8000/api/upload/" ^ -H "accept: application/json" ^ -H "Content-Type: multipart/form-data" ^ -F "file=@C:\Users\YourName\Downloads\Learn Python in Less than 10 Minutes for Beginners (Fast & Easy).mp4;type=video/mp4"
```

##### **Response Body**:
```
{
  "filename": "Learn Python in Less than 10 Minutes for Beginners (Fast & Easy).mp4",
  "file_id": "ab2ff54c-418f-409e-92fa-50de7f2f127c",
  "task_id": "4b61628c-80ae-4eac-97c0-39169f79a341"
}
```

##### **Response Body from a successful 'task_id' task completed in '/api/task-status/{task_id}'**:
```
{
  "task_id": "4b61628c-80ae-4eac-97c0-39169f79a341",
  "status": "SUCCESS",
  "result": {
    "filename": "Learn Python in Less than 10 Minutes for Beginners (Fast & Easy).mp4",
    "file_id": "ab2ff54c-418f-409e-92fa-50de7f2f127c",
    "path": "/app/uploads/ab2ff54c-418f-409e-92fa-50de7f2f127c_Learn Python in Less than 10 Minutes for Beginners (Fast & Easy).mp4"
  }
}
```

<<<<<<< HEAD
<<<<<<< HEAD
If it's a PDF file upload, task ID for "parse_summarise_ingest_task_id":
=======
## Endpoint: /api/get_video_uri
>>>>>>> 933d357 (Added DOCX and Flash Cards implementations)

#### Getting a Video URI Link

<<<<<<< HEAD
=======
>>>>>>> 143d6af (Initial Python service commit)
## /api/get_video_uri
=======
As I mentioned above, to uploaded videos we need to generate an URI link so we can later use it to chat with the video. To use this endpoint, you must first provide a file path to the video which can be obtained from the '/api/upload' endpoint. Then you can pass the whole "video" object this task returns to the '/api/process-ai` endpoint which we'll see later on.
>>>>>>> 933d357 (Added DOCX and Flash Cards implementations)

##### **Example usage**:
```
curl -X 'POST' \
  'http://localhost:8000/api/get_video_uri' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "video": {
    "type": "FILE_VIDEO",
    "path": "/app/uploads/ab2ff54c-418f-409e-92fa-50de7f2f127c_Learn Python in Less than 10 Minutes for Beginners (Fast & Easy).mp4"
  },
  "uri_provider": "gemini_uri_provider",
  "api_key": "<YOUR_API_KEY_HERE>"
}'
```

##### **Response Body**:
```
{
  "task_id": "ff40a2b2-e322-40a6-b46e-9704d822c392",
  "status": "PENDING"
}
```

##### **Response Body from a successful 'task_id' task completed in '/api/task-status/{task_id}'**:
```
{
  "task_id": "ff40a2b2-e322-40a6-b46e-9704d822c392",
  "status": "SUCCESS",
  "result": {
    "video": {
      "type": "FILE_VIDEO",
      "uri": "https://generativelanguage.googleapis.com/v1beta/files/b5wwz40uuiqf",
      "uri_data": {
        "uri_file_name": "files/b5wwz40uuiqf",
        "uri_state": "ACTIVE",
        "uri_mime_type": "video/mp4"
      },
      "path": "/app/uploads/ab2ff54c-418f-409e-92fa-50de7f2f127c_Learn Python in Less than 10 Minutes for Beginners (Fast & Easy).mp4",
      "duration": {
        "minutes": 10,
        "seconds": 30
      }
    },
    "uri_provider": "gemini_uri_provider",
    "api_key": "<YOUR_API_KEY_HERE>"
  }
}
```

## Endpoint: /api/process-ai

#### Basic Explanation

Now this is the most important endpoint as this is where the communication happens. The most basic explanation is that it works by having an object called "conversation_history" that's a list of human and model messages. You append the human/user's message at the end of this conversation history and start the endpoint. Then the endpoint will return the whole conversation history with the model/AI message response at the end of the list.

##### **Basic Example Usage**:
```
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
      }
    ]
  },
  "api_key": "<YOUR_API_KEY_HERE>"
}'
```

##### **Basic Response Body**:
```
{
  "task_id": "8ae26218-bf33-4d92-9717-1c4732f6088e",
  "status": "PENDING"
}
```

##### **Basic Response Body from a successful 'task_id' task completed in '/api/task-status/{task_id}'**:
```
{
  "task_id": "8ae26218-bf33-4d92-9717-1c4732f6088e",
  "status": "SUCCESS",
  "result": {
    "conversation_history": [
      {
        "role": "user",
        "content": "Who are you?",
        "video": null,
        "tool_calls": null,
        "tool_results": null
      },
      {
        "role": "model",
        "content": "I am Gemini, a large language model built by Google.  I can provide information and complete tasks as instructed, using the available Python libraries.",
        "video": null,
        "tool_calls": null,
        "tool_results": null
      }
    ]
  }
}
```

#### Advanced Explanation (Asking Questions about PDF/DOCX/Videos)

If you want to ask questions about the uploaded PDF, DOCX, or video object, you will need to provide it to the endpoint. Afterwards, in the last user message of the conversation history, specify within the message what file you're asking about.

In the example below, we are using a video object obtained from the '/api/get_video_uri' endpoint. You can also specify a "YOUTUBE_VIDEO" object in this endpoint like this:
```
"video": {
    "type": "YOUTUBE_VIDEO",
    "uri": "https://www.youtube.com/watch?v=fWjsdhR3z3c"
}
```

Sometimes the model will say "I don't have access to PDF/DOCX/Video file". In that case, specify in the user message "Use the PDF tool to answer this question: QUESTION". 

Note: don't look at the "tool_calls" and "tool_results" as they were not implemented yet in the messaging system, and will always be returned as null.

##### **Example usage**:
```
curl -X 'POST' \
  'http://localhost:8000/api/process-ai' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "model_provider": "gemini",
  "model_name": "gemini-1.5-flash",
  "system_instruction_text": "You are a helpful AI that explains educational content.",
  "pdf_collection_name": "29c0127f-6620-466d-967f-adabdda7597a",
  "docx_collection_name": "29c8025b-ab3f-4f70-8009-ff144c6cb435",
  "video": {
      "type": "FILE_VIDEO",
      "uri": "https://generativelanguage.googleapis.com/v1beta/files/b5wwz40uuiqf",
      "uri_data": {
        "uri_file_name": "files/b5wwz40uuiqf",
        "uri_state": "ACTIVE",
        "uri_mime_type": "video/mp4"
      },
      "path": "/app/uploads/ab2ff54c-418f-409e-92fa-50de7f2f127c_Learn Python in Less than 10 Minutes for Beginners (Fast & Easy).mp4",
      "duration": {
        "minutes": 10,
        "seconds": 30
      }
    },
  "conversation_history": {
    "messages": [
      {
        "role": "user",
        "content": "Who are you?",
        "video": null,
        "tool_calls": null,
        "tool_results": null
      },
      {
        "role": "model",
        "content": "I am Gemini, a large language model built by Google.  I can provide information and complete tasks as instructed, using the available Python libraries."
      },
      {
        "role": "user",
        "content": "In the video, can you tell me how are Python lists described?"
      },
      {
        "role": "model",
        "content": "Based on the video, Python lists are described as ordered, mutable (changeable) collections of items. They are created using square brackets `[]`, with items separated by commas.  Lists can contain items of different data types (e.g., numbers, strings, booleans).  The video demonstrated how to iterate through a list using a `for` loop."
      },
      {
        "role": "user",
        "content": "In the PDF file, can you tell me how does positional encoding help the model understand the order of tokens in sequences, and what alternatives could be used?"
      },
      {
        "role": "model",
        "content": "The PDF describes positional encoding as a method to inject information about the relative or absolute position of tokens in a sequence into the model, since the Transformer architecture lacks recurrence and convolution.  The described method uses sine and cosine functions of different frequencies:\n\n`PE(pos,2i) = sin(pos/10000^(2i/dmodel))`\n`PE(pos,2i+1) = cos(pos/10000^(2i/dmodel))`\n\nwhere `pos` is the position and `i` is the dimension.  The PDF mentions experimenting with learned positional embeddings as an alternative, finding nearly identical results.  The sinusoidal version was preferred due to its potential to extrapolate to longer sequences than those seen during training."
      },
      {
        "role": "user",
        "content": "In the Word DOCX file, can you tell me what are the key components of the Transformer'\''s encoder and decoder stacks?"
      }
    ]
  },
  "api_key": "<YOUR_API_KEY_HERE>"
}'
```

##### **Response Body**:
```
{
  "task_id": "4a735938-163b-41b8-ae48-e145cd172ffa",
  "status": "PENDING"
}
```

##### **Response Body from a successful 'task_id' task completed in '/api/task-status/{task_id}'**:
```
{
  "task_id": "4a735938-163b-41b8-ae48-e145cd172ffa",
  "status": "SUCCESS",
  "result": {
    "conversation_history": [
      {
        "role": "user",
        "content": "Who are you?",
        "video": null,
        "tool_calls": null,
        "tool_results": null
      },
      {
        "role": "model",
        "content": "I am Gemini, a large language model built by Google.  I can provide information and complete tasks as instructed, using the available Python libraries.",
        "video": null,
        "tool_calls": null,
        "tool_results": null
      },
      {
        "role": "user",
        "content": "In the video, can you tell me how are Python lists described?",
        "video": null,
        "tool_calls": null,
        "tool_results": null
      },
      {
        "role": "model",
        "content": "Based on the video, Python lists are described as ordered, mutable (changeable) collections of items. They are created using square brackets `[]`, with items separated by commas.  Lists can contain items of different data types (e.g., numbers, strings, booleans).  The video demonstrated how to iterate through a list using a `for` loop.",
        "video": null,
        "tool_calls": null,
        "tool_results": null
      },
      {
        "role": "user",
        "content": "In the PDF file, can you tell me how does positional encoding help the model understand the order of tokens in sequences, and what alternatives could be used?",
        "video": null,
        "tool_calls": null,
        "tool_results": null
      },
      {
        "role": "model",
        "content": "The PDF describes positional encoding as a method to inject information about the relative or absolute position of tokens in a sequence into the model, since the Transformer architecture lacks recurrence and convolution.  The described method uses sine and cosine functions of different frequencies:\n\n`PE(pos,2i) = sin(pos/10000^(2i/dmodel))`\n`PE(pos,2i+1) = cos(pos/10000^(2i/dmodel))`\n\nwhere `pos` is the position and `i` is the dimension.  The PDF mentions experimenting with learned positional embeddings as an alternative, finding nearly identical results.  The sinusoidal version was preferred due to its potential to extrapolate to longer sequences than those seen during training.",
        "video": null,
        "tool_calls": null,
        "tool_results": null
      },
      {
        "role": "user",
        "content": "In the Word DOCX file, can you tell me what are the key components of the Transformer's encoder and decoder stacks?",
        "video": null,
        "tool_calls": null,
        "tool_results": null
      },
      {
        "role": "model",
        "content": "According to the DOCX file, the key components of the Transformer's encoder and decoder stacks are:\n\n* **Multi-Head Self-Attention:** This mechanism allows the model to weigh the importance of different words in a sequence when processing each word.  The DOCX highlights its ability to capture relationships between words regardless of their distance.\n\n* **Feed-Forward Networks:** Positioned after the self-attention layers, these fully connected networks further process the information from the self-attention.  The DOCX emphasizes their role in non-linear transformations.\n\n* **Add & Norm Layers:** These layers, present after both the self-attention and feed-forward networks, perform residual connections (adding the input to the output) and layer normalization to stabilize training and improve performance.  The DOCX describes their role in preventing vanishing gradients.\n\n* **Encoder-Decoder Interaction:** In the overall Transformer architecture, the encoder's output is used as input to the decoder.  The DOCX explains how the decoder uses this information along with its own self-attention to generate the output sequence.\n\n\nThe DOCX may also have further details on specific aspects of these components, such as the number of layers in the stacks or the specific configurations of the feed-forward networks and attention heads.",
        "video": null,
        "tool_calls": null,
        "tool_results": null
      }
    ]
  }
}
```

## Endpoint: /api/create_flash_cards

Although you can get flash cards from the '/api/upload' endpoint, this endpoint also exists as a way for me to test out the functionalities. You can use it too if needed!

KEEP IN MIND: Here you can only provide a collection name OR a video object, but not both at the same time!

##### **Example Usage Collection Name**:
```
curl -X 'POST' \
  'http://localhost:8000/api/create_flash_cards?collection_name=29c0127f-6620-466d-967f-adabdda7597a&api_key=<YOUR_API_KEY_HERE>' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d ''
```

##### **Example Usage Video Object**:
```
curl -X 'POST' \
  'http://localhost:8000/api/create_flash_cards?api_key=<YOUR_API_KEY_HERE>' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "type": "YOUTUBE_VIDEO",
  "uri": "https://www.youtube.com/watch?v=fWjsdhR3z3c"
}'
```

##### **Response Body**:
```
{
  "task_id": "e262837e-e128-458e-a98b-0c19a0aebf52",
  "status": "PENDING"
}
```

##### **Response Body from a successful 'task_id' task completed in '/api/task-status/{task_id}'**:
```
{
  "task_id": "e262837e-e128-458e-a98b-0c19a0aebf52",
  "status": "SUCCESS",
  "result": {
    "flash_cards": [
      {
        "question": "What is the primary architectural innovation of the Transformer model as described in the text?",
        "answer": "The Transformer replaces recurrent and convolutional layers commonly used in encoder-decoder architectures with multi-headed self-attention, making it entirely attention-based."
      },
      {
        "question": "Based on Table 2, how does the Transformer's performance compare to other models on the WMT 2014 English-to-German translation task?",
        "answer": "The 'big' Transformer model outperforms all previously reported models (including ensembles) by more than 2.0 BLEU points, achieving a new state-of-the-art BLEU score of 28.4."
      },
      {
        "question": "What is the purpose of the '<pad>' tokens in the parallel coordinate plot visualization?",
        "answer": "The '<pad>' tokens are padding tokens used to make all input sequences the same length, a common practice in machine learning models to ensure consistent input dimensions."
      },
      {
        "question": "Describe the role of residual connections and layer normalization in the Transformer's encoder and decoder stacks.",
        "answer": "Residual connections (x + Sublayer(x)) and layer normalization are used around each sub-layer in both the encoder and decoder.  They help improve the training of deep networks by mitigating the vanishing gradient problem and stabilizing the learning process."
      },
      {
        "question": "What training data was primarily used by the models presented in the table showing WSJ 23 FI results?",
        "answer": "The models primarily used the WSJ corpus for training, with some using semi-supervised or multi-task learning approaches in addition to discriminative training."
      }
    ]
  }
}
```