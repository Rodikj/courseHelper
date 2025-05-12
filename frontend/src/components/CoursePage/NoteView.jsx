import React from 'react';

const NoteView = ({ note, onClose }) => {
  if (!note) return null;

  return (
    <div className="flex flex-col h-full">
      <div className="flex justify-between items-center border-y-1 border-[#ECF0FA] mb-4">
        <h3 className="text-lg font-bold">{note.name}</h3>
      </div>
      
      <div className="flex-1 overflow-y-auto bg-gray-50 rounded-md p-4 mb-4">
        <div className="whitespace-pre-wrap">{note.content}</div>
      </div>
    </div>
  );
};

export default NoteView;