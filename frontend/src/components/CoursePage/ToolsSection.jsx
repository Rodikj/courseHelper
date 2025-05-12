import React, { useState } from 'react';
import NoteModal from './NoteModal';
import NoteView from './NoteView';
import { TbCardsFilled } from "react-icons/tb";
import { LuNotebookPen } from "react-icons/lu";



const ToolsSection = ({note}) => {
  const [notes, setNotes] = useState([]);
  const [flashcards, setFlashcards] = useState([]);
  const [isNoteModalOpen, setIsNoteModalOpen] = useState(false);
  const [selectedNote, setSelectedNote] = useState(null);

  const handleAddNote = () => {
    setIsNoteModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsNoteModalOpen(false);
    setSelectedNote(null);
  };

  const handleSaveNote = (note) => {
    setNotes([...notes, note]);
  };

  const handleDeleteNote = (noteId) => {
    setNotes(notes.filter(note => note.id !== noteId));
  };

  const handleViewNote = (note) => {
    setSelectedNote(note);
  };

  return (
    <div className="flex-1 flex flex-col overflow-hidden">
      <div className="flex justify-between items-center mb-4">
        {selectedNote ? (
          <h2 className="text-xl font-semibold">Tools &gt; Notes</h2>
        ) : (
          <h2 className="text-xl font-semibold">Tools</h2>
        )}
        {selectedNote && (
          <button
            onClick={() => setSelectedNote(null)}
            className="text-gray-500 hover:text-gray-700 hover:cursor-pointer"
          >
            Back
          </button>
        )}
      </div>

      {!selectedNote ? (
        <>
          <button
            onClick={handleAddNote}
            className="bg-blue-500 hover:cursor-pointer hover:bg-blue-600 text-white rounded-md py-2 mb-4"
          >
            + Add note
          </button>

          <button className="bg-blue-500 hover:bg-blue-600 hover:cursor-pointer text-white rounded-md py-2 mb-4 flex items-center justify-center">
            <span className="mr-2">
              <TbCardsFilled />
            </span>
            Flashcards set
          </button>
          <div className="border-b-1 border-[#ECF0FA] mb-10">
            <br />
          </div>

          <div className="flex-1 overflow-y-auto">
            {notes.length > 0 &&
              notes.map((note) => (
                <div
                  key={note.id}
                  className="bg-blue-500 hover:opacity-90 text-white p-3 rounded-md mb-2 flex items-center cursor-pointer"
                  onClick={() => handleViewNote(note)}
                >
                  <span className="mr-2">
                    <LuNotebookPen />
                  </span>
                  <span className="flex-1 truncate">{note.name}</span>
                  <button
                    onClick={(e) => {
                      e.stopPropagation();
                      handleDeleteNote(note.id);
                    }}
                    className="text-white opacity-80 hover:opacity-100"
                  >
                    <div className="hover:cursor-pointer">×</div>
                  </button>
                </div>
              ))}

            {flashcards.length > 0 && (
              <div className="bg-blue-500 text-white p-3 rounded-md mb-2 flex items-center">
                <span className="mr-2"><TbCardsFilled /></span>
                <span className="flex-1 truncate">Flashcards set</span>
                <button className="text-white opacity-80 hover:opacity-100">
                  <div className="bg-gray-300 w-6 h-6 rounded-2xl">×</div>
                </button>
              </div>
            )}
          </div>
        </>
      ) : (
        <NoteView note={selectedNote} onClose={() => setSelectedNote(null)} />
      )}

      <NoteModal
        isOpen={isNoteModalOpen}
        onClose={handleCloseModal}
        onSave={handleSaveNote}
      />
    </div>
  );
};

export default ToolsSection;