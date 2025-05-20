import React, { useState, useRef } from 'react';
import axios from 'axios';
import { IoMdClose } from "react-icons/io";
import { FaLink } from "react-icons/fa6";
import { BsCardText } from "react-icons/bs";
import { FaYoutube } from "react-icons/fa";
import { BsUpload } from "react-icons/bs";
import { IoArrowBack } from "react-icons/io5";

const UploadModal = ({ 
  onClose, 
  onAddUrl, 
  onAddTextSource, 
  userId, 
  selectedCourseId,
  onUploadSuccess
}) => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [currentView, setCurrentView] = useState('main'); // 'main', 'youtube', 'text'
  const [youtubeUrl, setYoutubeUrl] = useState('');
  const [urlError, setUrlError] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);
  const fileInputRef = useRef(null);

  // YouTube ID extraction function
  const getYoutubeVideoId = (url) => {
    // Match patterns like youtube.com/watch?v=VIDEO_ID or youtu.be/VIDEO_ID
    const regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#&?]*).*/;
    const match = url.match(regExp);
    
    return (match && match[7].length === 11) ? match[7] : null;
  };

  // Validate YouTube URL
  const validateUrl = (url) => {
    if (!url.trim()) {
      return "Please enter a URL";
    }
    
    if (!url.includes('youtube.com') && !url.includes('youtu.be')) {
      return "Please enter a valid YouTube URL";
    }
    
    if (!getYoutubeVideoId(url)) {
      return "Could not extract YouTube video ID";
    }
    
    return null;
  };

  const onUploadFile = async (e) => {
    const file = e.target.files[0];
    if (!file) return;

    setSelectedFile(file);

    const formData = new FormData();
    formData.append("file", file);
    formData.append("userId", userId);

    try {
      const response = await axios.post(
        `http://localhost:8080/api/courses/${selectedCourseId}/files`,
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );

      console.log("Uploaded:", response.data);

      // Optionally notify parent
      if (onUploadSuccess) {
        onUploadSuccess(response.data);
      }

      onClose(); // Close modal on success
    } catch (error) {
      console.error("Upload failed:", error);
      alert("Upload failed: " + (error.response?.data?.message || "Unknown error"));
    }
  };

  // Handle YouTube URL submission
  const handleYoutubeSubmit = async () => {
    // Validate URL
    const validationError = validateUrl(youtubeUrl);
    if (validationError) {
      setUrlError(validationError);
      return;
    }
    
    const ytId = getYoutubeVideoId(youtubeUrl);
    
    setIsSubmitting(true);
    try {
      const response = await axios.post(
        `http://localhost:8080/api/courses/${selectedCourseId}/videos`,
        {
          userId,
          ytId,
          fileName: `YouTube Video (${ytId})`,
          fileType: 'youtube'
        }
      );
      
      console.log("YouTube link added:", response.data);
      
      // Notify parent of success
      if (onUploadSuccess) {
        onUploadSuccess(response.data);
      }
      
      onClose(); // Close modal on success
    } catch (error) {
      console.error("Failed to save YouTube URL:", error);
      setUrlError(`Failed to save URL: ${error.response?.data?.message || "Unknown error"}`);
    } finally {
      setIsSubmitting(false);
    }
  };

  // Render main view with all options
  const renderMainView = () => (
    <>
      {/* Upload box */}
      <div className="border-2 border-dashed border-gray-300 rounded-xl py-8 sm:py-12 px-4 sm:px-6 flex flex-col items-center text-center mb-6">
        <p className="font-semibold text-gray-800">Upload Your Materials</p>
        <p className="text-sm text-gray-500">
          Select{" "}
          <span
            className="text-[#2D84FF] hover:underline cursor-pointer"
            onClick={() => fileInputRef.current?.click()}
          >
            choose file
          </span>{" "}
          to upload
        </p>
        <p className="text-sm text-gray-400 mt-1">
          Supported file types: PDF, .txt, .docx
        </p>
        <input
          type="file"
          ref={fileInputRef}
          onChange={onUploadFile}
          accept=".pdf,.docx,.txt"
          className="hidden"
        />
        {selectedFile && (
          <p className="mt-4 text-sm text-gray-600">
            Selected: {selectedFile.name}
          </p>
        )}
      </div>

      {/* Source options */}
      <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
        {/* Link Box */}
        <div
          onClick={() => setCurrentView('youtube')}
          className="border-2 border-dashed border-gray-300 rounded-xl p-4 cursor-pointer hover:bg-gray-50 transition"
        >
          <p className="font-medium flex mb-2">
            <div className="pr-2 text-xl">
              <FaLink />
            </div>
            Link
          </p>
          <span className="text-xs text-white bg-[#6767FD] px-2 py-1 rounded">
            YouTube
          </span>
        </div>

        {/* Paste Text Box */}
        <div
          onClick={() => setCurrentView('text')}
          className="border-2 border-dashed border-gray-300 rounded-xl p-4 cursor-pointer hover:bg-gray-50 transition"
        >
          <p className="font-medium flex mb-2">
            <div className='text-xl pr-2'>
              <BsCardText />
            </div>{" "}
            Paste text
          </p>
          <span className="text-xs text-white bg-[#A3A3A3] px-2 py-1 rounded">
            Copied text
          </span>
        </div>
      </div>
    </>
  );

  // Render YouTube URL input view
  const renderYoutubeView = () => (
    <>
      <div className="flex items-center mb-4">
        <button 
          onClick={() => setCurrentView('main')}
          className="mr-2 text-gray-500 hover:text-gray-700"
        >
          <IoArrowBack size={20} />
        </button>
        <h3 className="text-lg font-semibold">Add YouTube Link</h3>
      </div>

      <div className="mb-6">
        <div className="flex items-center mb-2">
          <FaYoutube className="text-red-600 mr-2" />
          <label htmlFor="youtube-url" className="text-gray-700 font-medium">
            YouTube URL
          </label>
        </div>
        <input
          id="youtube-url"
          type="text"
          className="w-full border-2 border-gray-300 rounded-xl p-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
          placeholder="https://www.youtube.com/watch?v=..."
          value={youtubeUrl}
          onChange={(e) => {
            setYoutubeUrl(e.target.value);
            setUrlError('');
          }}
          onKeyPress={(e) => {
            if (e.key === 'Enter') {
              handleYoutubeSubmit();
            }
          }}
        />
        {urlError && <p className="text-red-500 text-sm mt-1">{urlError}</p>}
      </div>

      <div className="mt-6 flex justify-end space-x-3">
        <button
          type="button"
          onClick={() => setCurrentView('main')}
          className="px-4 py-2 border border-gray-300 rounded-xl text-gray-700 hover:bg-gray-50"
          disabled={isSubmitting}
        >
          Cancel
        </button>
        <button
          type="button"
          onClick={handleYoutubeSubmit}
          className="px-4 py-2 bg-blue-500 text-white rounded-xl hover:bg-blue-600 disabled:bg-blue-300"
          disabled={isSubmitting}
        >
          {isSubmitting ? "Saving..." : "Save URL"}
        </button>
      </div>
    </>
  );

  // Placeholder for text view (to be implemented)
  const renderTextView = () => (
    <>
      <div className="flex items-center mb-4">
        <button 
          onClick={() => setCurrentView('main')}
          className="mr-2 text-gray-500 hover:text-gray-700"
        >
          <IoArrowBack size={20} />
        </button>
        <h3 className="text-lg font-semibold">Add Text Source</h3>
      </div>
      
      {/* This would be expanded with text input functionality */}
      <div className="p-4 border border-gray-300 rounded-xl">
        <p className="text-center text-gray-500">Text input functionality would go here</p>
      </div>
      
      <div className="mt-6 flex justify-end space-x-3">
        <button
          type="button"
          onClick={() => setCurrentView('main')}
          className="px-4 py-2 border border-gray-300 rounded-xl text-gray-700 hover:bg-gray-50"
        >
          Cancel
        </button>
      </div>
    </>
  );

  return (
    <div className="fixed inset-0 bg-transparent backdrop-blur-sm flex items-center justify-center z-50 p-4">
      <div className="bg-white rounded-2xl shadow-2xl p-4 sm:p-8 w-full max-w-2xl">
        <div className="flex justify-between items-start mb-6">
          <div>
            <h2 className="text-xl sm:text-2xl text-[#2D84FF] font-bold">
              Course Helper
            </h2>
            <h3 className="text-lg sm:text-xl font-semibold mt-1">
              Add source
            </h3>
          </div>
          <button
            onClick={onClose}
            className="text-gray-500 hover:text-black text-2xl hover:cursor-pointer font-bold"
          >
            <div className="bg-gray-300 w-8 h-8 rounded-2xl flex justify-center items-center hover:cursor-pointer hover:bg-gray-200">
              <IoMdClose />
            </div>
          </button>
        </div>

        {/* Conditional rendering based on currentView */}
        {currentView === 'main' && renderMainView()}
        {currentView === 'youtube' && renderYoutubeView()}
        {currentView === 'text' && renderTextView()}
      </div>
    </div>
  );
};

export default UploadModal;