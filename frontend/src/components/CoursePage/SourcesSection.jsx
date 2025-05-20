import React, { useState } from 'react';
import { FaYoutube } from 'react-icons/fa';

const SourcesSection = ({ sources, onAddSource, onDeleteSource }) => {
  const [expandedSourceId, setExpandedSourceId] = useState(null);

  const getSourceIcon = (type, ytId) => {
    if (ytId) {
      return <FaYoutube className="text-red-500" />;
    }
    
    switch(type) {
      case 'pdf':
        return '📄';
      case 'youtube':
        return '🌐';
      case 'text':
        return '📝';
      default:
        return '📄';
    }
  };

  const toggleSourceExpand = (sourceId) => {
    if (expandedSourceId === sourceId) {
      setExpandedSourceId(null);
    } else {
      setExpandedSourceId(sourceId);
    }
  };

  // YouTube preview component
  const YouTubePreview = ({ videoId }) => (
    <div className="p-3 bg-gray-100 rounded-lg">
      <div className="aspect-video bg-black flex items-center justify-center rounded overflow-hidden mb-2">
        <div className="flex flex-col items-center justify-center">
          <FaYoutube className="text-red-600 text-4xl mb-2" />
          <span className="text-white text-sm">youtube.com/watch?v={videoId}</span>
        </div>
      </div>
      <div className="flex justify-between items-center">
        <span className="text-sm text-gray-500">Click to open in YouTube</span>
        <a 
          href={`https://www.youtube.com/watch?v=${videoId}`} 
          target="_blank" 
          rel="noopener noreferrer"
          className="text-blue-500 text-sm hover:underline"
          onClick={(e) => e.stopPropagation()}
        >
          Open video
        </a>
      </div>
    </div>
  );

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
          <div key={source.id}>
            <div
              className={`bg-white border border-gray-200 hover:border-blue-300 p-3 rounded-lg mb-2 flex items-center cursor-pointer ${expandedSourceId === source.id ? 'border-blue-500' : ''}`}
              onClick={() => toggleSourceExpand(source.id)}
            >
              <span className="mr-2">{getSourceIcon(source.type, source.ytId)}</span>
              <span className="flex-1 truncate">{source.name}</span>
              <button
                onClick={(e) => {
                  e.stopPropagation();
                  onDeleteSource(source.id);
                }}
                className="text-gray-500 hover:text-red-500"
              >
                <div className='hover:cursor-pointer'>×</div>
              </button>
            </div>
            
            {expandedSourceId === source.id && source.ytId && (
              <div className="mb-3 -mt-1 rounded-b-lg overflow-hidden border-l border-r border-b border-gray-200">
                <YouTubePreview videoId={source.ytId} />
              </div>
            )}
          </div>
        ))}
        
        {sources.length === 0 && (
          <div className="text-center text-gray-500 p-4">
            No sources added. Click "+ Add Sources" to begin.
          </div>
        )}
        
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