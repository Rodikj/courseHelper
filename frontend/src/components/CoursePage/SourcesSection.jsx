import React from 'react';

const SourcesSection = ({ sources, onAddSource, onDeleteSource }) => {
  const getSourceIcon = (type) => {
    switch(type) {
      case 'pdf':
        return '📄';
      case 'url':
        return '🌐';
      case 'text':
        return '📝';
      default:
        return '📄';
    }
  };

  return (
    <div className="flex-1 flex flex-col overflow-hidden">
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-xl font-semibold">Sources</h2>
          {/* collapse window should be here */}
      </div>
      
      {sources.length < 10 ? (
        <button
          onClick={onAddSource}
          className="bg-blue-500 hover:cursor-pointer hover:bg-blue-600 text-white rounded-2xl py-2 mb-4"
        >
          + Add Sources
        </button>
      ) : (
        <h1 className='text-red-400 mb-5 animate-pulse'>* Sources limit reached</h1>
      )}
      
      <div className="flex-1 overflow-y-auto">
        {sources.map((source) => (
          <div
            key={source.id}
            className="bg-blue-500 hover:opacity-90 text-white p-3 rounded-md mb-2 flex items-center"
          >
            <span className="mr-2">{getSourceIcon(source.type)}</span>
            <span className="flex-1 truncate">{source.name}</span>
            <button
              onClick={() => onDeleteSource(source.id)}
              className="text-white opacity-80 hover:opacity-100"
            >
              <div className='hover:cursor-pointer'>×</div>
            </button>
          </div>
        ))}
        
        {sources.length > 0 && (
          <div className="text-center text-gray-500 mt-4">
            {sources.length}/10
          </div>
        )}
      </div>
    </div>
  );
};

export default SourcesSection;