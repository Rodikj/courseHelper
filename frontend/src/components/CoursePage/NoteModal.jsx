import React, { useState } from 'react';
import { IoMdClose } from "react-icons/io";

const NoteModal = ({ isOpen, onClose, onSave }) => {
  const [noteName, setNoteName] = useState('');
  const [noteContent, setNoteContent] = useState('');

  const handleSave = () => {
    if (noteName.trim() && noteContent.trim()) {
      onSave({
        id: Date.now().toString(),
        name: noteName,
        content: noteContent
      });
      setNoteName('');
      setNoteContent('');
      onClose();
    }
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-tansparent backdrop-blur-sm flex items-center justify-center z-50">
      <div className="bg-white rounded-lg shadow-lg w-full max-w-md mx-4">
        <div className="flex justify-between items-center  p-4">
          <h3 className="text-xl text-blue-500 font-semibold">Add New Note</h3>
          <button 
            onClick={onClose}
            className="text-gray-500 hover:text-gray-700 text-2xl"
          >
            <div className='bg-gray-300 w-8 h-8 rounded-2xl flex justify-center items-center hover:cursor-pointer hover:bg-gray-200'>
              <IoMdClose />
            </div>
          </button>
        </div>
        
        <div className="p-4">
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              Note Name
            </label>
            <input
              type="text"
              value={noteName}
              onChange={(e) => setNoteName(e.target.value)}
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              placeholder="Enter note name..."
            />
          </div>
          
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-bold mb-2">
              Note Content
            </label>
            <textarea
              value={noteContent}
              onChange={(e) => setNoteContent(e.target.value)}
              className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline h-32"
              placeholder="Write your note here..."
            />
          </div>
          
          <div className="flex justify-end">
            <button
              onClick={handleSave}
              className="bg-blue-500 hover:bg-blue-600 text-white font-bold hover:cursor-pointer py-2 px-4 rounded"
            >
              Save Note
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default NoteModal;