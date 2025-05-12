import React from 'react';
import { IoMdClose } from "react-icons/io";
import { FaLink } from "react-icons/fa6";
import { BsCardText } from "react-icons/bs";



const UploadModal = ({ 
  onClose, 
  onUploadFile, 
  onAddUrl, 
  onAddTextSource, 
  selectedFile, 
  fileInputRef 
}) => {
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
            Supported file types: PDF, .txt, docx
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
            onClick={onAddUrl}
            className="border-2 border-dashed border-gray-300 rounded-xl p-4 cursor-pointer hover:bg-gray-50 transition"
          >
            <p className="font-medium flex mb-2">
              <div className="pr-2 text-xl">
                <FaLink className="" />
              </div>
              Link
            </p>
            <span className="text-xs text-white bg-[#6767FD] px-2 py-1 rounded">
              YouTube
            </span>
          </div>

          {/* Paste Text Box */}
          <div
            onClick={onAddTextSource}
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
      </div>
    </div>
  );
};

export default UploadModal;