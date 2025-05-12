import React, { useState, useRef, useEffect } from 'react';

const ChatSection = ({ chatMessages, onSendMessage, onClearChat }) => {
  const [message, setMessage] = useState('');
  const chatContainerRef = useRef(null);
  
  useEffect(() => {
    if (chatContainerRef.current) {
      chatContainerRef.current.scrollTop = chatContainerRef.current.scrollHeight;
    }
  }, [chatMessages]);
  
  const handleSendClick = () => {
    if (message.trim()) {
      onSendMessage(message);
      setMessage('');
    }
  };

  return (
    <div className="flex-1 flex flex-col overflow-hidden">
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-xl font-semibold">Chat</h2>
        <button
          onClick={onClearChat}
          className="text-gray-500 hover:text-gray-700 hover:cursor-pointer bg-gray-200 px-3 py-1 rounded-md text-sm"
        >
          Clear chat
        </button>
      </div>
      
      <div
        ref={chatContainerRef}
        className="flex-1 overflow-y-auto mb-4 space-y-4"
      >
        {chatMessages.map((msg) => (
          <div
            key={msg.id}
            className={`p-3 rounded-lg ${
              msg.sender === "user"
                ? "bg-blue-100 ml-8 sm:ml-12"
                : "bg-gray-200 mr-8 sm:mr-12"
            }`}
          >
            {msg.text}
          </div>
        ))}
        
        {chatMessages.length === 0 && (
          <div className="text-center text-gray-400 italic mt-8">
            Ask questions about your uploaded materials
          </div>
        )}
      </div>
      
      <div className="relative">
        <input
          type="text"
          value={message}
          onChange={(e) => setMessage(e.target.value)}
          onKeyPress={(e) => e.key === "Enter" && handleSendClick()}
          placeholder="Start Typing ..."
          className="w-full border border-gray-300 rounded-xl p-3 pr-12"
        />
        <button
          onClick={handleSendClick}
          className="absolute right-3 top-1/2 transform -translate-y-1/2 text-blue-500"
        >
          <div className='bg-gray-200 w-6 h-6 hover:opacity-75 hover:cursor-pointer rounded-2xl flex items-center justify-center'>
            ➤
          </div>
        </button>
      </div>
    </div>
  );
};

export default ChatSection;