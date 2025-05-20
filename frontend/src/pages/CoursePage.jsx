import { useState, useRef, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import SourcesSection from '../components/CoursePage/SourcesSection';
import ToolsSection from '../components/CoursePage/ToolsSection';
import ChatSection from '../components/CoursePage/ChatSection';
import UploadModal from '../components/CoursePage/UploadModal';


const CoursePage = () => {
  const [sources, setSources] = useState([]);
  const [chatMessages, setChatMessages] = useState([]);
  const [notes, setNotes] = useState([]);
  const [flashcards, setFlashcards] = useState([]);
  const [uploadModalOpen, setUploadModalOpen] = useState(false);
  const [courseName, setCourseName] = useState('');
  const [courseId, setCourseId] = useState(null);
  const currentUserId = localStorage.getItem('userId');
  const [activeMobileTab, setActiveMobileTab] = useState('chat');
  const fileInputRef = useRef(null);
  const [selectedFile, setSelectedFile] = useState(null);

  const navigate = useNavigate();
  
   // Extract course ID and name from URL parameters
   useEffect(() => {
    // Get URL search params
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');
    const name = urlParams.get('name');
    
    if (id) {
      setCourseId(id);
      
      // If name is provided in the URL, use it
      if (name) {
        setCourseName(decodeURIComponent(name));
      } else {
        // Try to get course name from localStorage as a fallback
        const savedCourses = localStorage.getItem("courses");
        if (savedCourses) {
          const coursesList = JSON.parse(savedCourses);
          const course = coursesList.find(
            (c) => c.id.toString() === id.toString()
          );
          if (course) {
            setCourseName(course.name);
          } else {
            setCourseName(`Course ${id}`);
          }
        } else {
          setCourseName(`Course ${id}`);
        }
      }
      
      // Load any saved data for this course
      const savedSources = localStorage.getItem(`course_${id}_sources`);
      const savedNotes = localStorage.getItem(`course_${id}_notes`);
      const savedFlashcards = localStorage.getItem(`course_${id}_flashcards`);
      
      if (savedSources) setSources(JSON.parse(savedSources));
      if (savedNotes) setNotes(JSON.parse(savedNotes));
      if (savedFlashcards) setFlashcards(JSON.parse(savedFlashcards));
    }
  }, []);
  
  // Save data when it changes
  useEffect(() => {
    if (courseId) {
      localStorage.setItem(`course_${courseId}_sources`, JSON.stringify(sources));
      localStorage.setItem(`course_${courseId}_notes`, JSON.stringify(notes));
      localStorage.setItem(`course_${courseId}_flashcards`, JSON.stringify(flashcards));
    }
  }, [courseId, sources, notes, flashcards]);

  const handleAddSource = () => {
    setUploadModalOpen(true);
  };

  const handleUploadFile = (e) => {
    const file = e.target.files[0];
    if (!file) return;
    
    setSelectedFile(file);
    
    // Simple file type detection
    let type = '';
    if (file.name.endsWith('.pdf')) {
      type = 'pdf';
    } else if (file.name.endsWith('.docx')) {
      type = 'docx';
    } else if (file.name.match(/\.(txt|text)$/i)) {
      type = 'text';
    } else if (file.name.match(/\.(html|htm)$/i) || file.type.includes('html')) {
      type = 'url';
    } else {
      type = 'text'; // Default fallback
    }
    
    // Create reader to get file content
    const reader = new FileReader();
    reader.onload = (e) => {
      const newSource = {
        id: Date.now(),
        name: file.name,
        type: type,
        content: e.target.result,
      };
      
      setSources([...sources, newSource]);
      
      // Auto-generate flashcards based on content
      if (flashcards.length === 0) {
        generateFlashcards(newSource);
      }
      
      setUploadModalOpen(false);
      setSelectedFile(null);
      if (fileInputRef.current) {
        fileInputRef.current.value = '';
      }
    };
    
    reader.readAsText(file);
  };

  const handleAddUrl = () => {
    const url = prompt("Enter website URL:");
    if (url) {
      const newSource = {
        id: Date.now(),
        name: url,
        type: 'url',
        content: url
      };
      setSources([...sources, newSource]);
    }
  };

  const handleAddTextSource = () => {
    const text = prompt("Enter or paste text content:");
    if (text) {
      const newSource = {
        id: Date.now(),
        name: 'Text Source',
        type: 'text',
        content: text
      };
      setSources([...sources, newSource]);
    }
  };

  const handleSendMessage = (message) => {
    if (!message.trim()) return;
    
    // Add user message
    const newUserMessage = {
      id: Date.now(),
      sender: 'user',
      text: message
    };
    
    setChatMessages([...chatMessages, newUserMessage]);
    
    // Simulate AI response
    setTimeout(() => {
      const sourceContent = sources.map(s => s.content).join(' ');
      
      // Very basic response simulation based on keywords
      let response = "I'm analyzing your materials. What specific information are you looking for?";
      
      if (message.toLowerCase().includes('summary')) {
        response = "Based on your materials, here's a summary: This appears to be course content related to " + 
                   courseName + ". The main concepts covered include learning objectives, key terms, and practical applications.";
      } else if (message.toLowerCase().includes('concept') || message.toLowerCase().includes('explain')) {
        response = "The key concepts in your materials involve understanding the fundamental principles and their practical applications.";
      } else if (sourceContent.length < 100) {
        response = "Your uploaded materials don't contain enough content for me to provide a detailed response. Consider uploading more comprehensive sources.";
      }
      
      const aiMessage = {
        id: Date.now(),
        sender: 'ai',
        text: response
      };
      
      setChatMessages(prev => [...prev, aiMessage]);
    }, 1000);
  };

  const handleAddNote = () => {
    const noteName = prompt("Enter note name:");
    if (noteName) {
      const newNote = {
        id: Date.now(),
        name: noteName,
        content: '',
        dateCreated: new Date().toLocaleDateString()
      };
      setNotes([...notes, newNote]);
    }
  };

  const generateFlashcards = (source) => {
    // In a real app, this would use AI to extract key terms and definitions
     //implement logic with backend & python services to extract flashcards

    // Here we just create sample mock flashcards
    const sampleFlashcards = [
      { id: Date.now(), term: 'Key Concept 1', definition: 'Definition for concept 1' },
      { id: Date.now() + 1, term: 'Key Term 2', definition: 'Explanation of term 2' },
      { id: Date.now() + 2, term: 'Important Idea 3', definition: 'Details about idea 3' }
    ];
    
    setFlashcards(sampleFlashcards);
  };

  const handleClearChat = () => {
    setChatMessages([]);
  };
  
  const handleDeleteSource = (id) => {
    setSources(sources.filter(source => source.id !== id));
  };
  
  const handleDeleteNote = (id) => {
    setNotes(notes.filter(note => note.id !== id));
  };

  // Render different content based on active mobile tab
  const renderMobileContent = () => {
    switch (activeMobileTab) {
      case 'sources':
        return (
          <SourcesSection 
            sources={sources}
            onAddSource={handleAddSource}
            onDeleteSource={handleDeleteSource}
          />
        );
        
      case 'tools':
        return (
          <ToolsSection 
            notes={notes}
            flashcards={flashcards}
            onAddNote={handleAddNote}
            onDeleteNote={handleDeleteNote}
          />
        );
        
      default: // 'chat' tab
        return (
          <ChatSection 
            chatMessages={chatMessages}
            onSendMessage={handleSendMessage}
            onClearChat={handleClearChat}
          />
        );
    }
  };

  return (
    <div className="flex flex-col h-screen bg-[#ECF0FA]">
      {/* Header */}
      <header className="flex flex-col sm:flex-row justify-between px-4 py-3 gap-2">
        <h1 className="text-2xl sm:text-3xl font-bold truncate">{courseName}</h1>
        <div className="flex gap-2 justify-between">
          <button
            onClick={() => navigate("/dashboard")}
            className="flex items-center bg-gray-800 hover:bg-gray-700 text-white rounded-2xl hover:cursor-pointer px-3 py-1 sm:px-4 sm:py-2 text-sm sm:text-base"
          >
            Dashboard
          </button>
          {/* <button className="flex items-center bg-gray-200 rounded-full p-1 sm:p-2 w-8 h-8 sm:w-10 sm:h-10 justify-center">
            👤
          </button> */}
        </div>
      </header>

      {/* Main content - responsive design */}
      <div className="flex-1 flex flex-col mx-2 sm:mx-4 mb-4 overflow-hidden">
        {/* Desktop version - three columns */}
        <div className="hidden sm:flex gap-4 h-full">
          {/* Sources section */}
          <div className="w-1/3 bg-gray-50 rounded-lg p-4 flex flex-col overflow-hidden">
            <SourcesSection 
              sources={sources}
              onAddSource={handleAddSource}
              onDeleteSource={handleDeleteSource}
            />
          </div>

          {/* Chat section */}
          <div className="w-1/2 bg-gray-50 rounded-lg p-4 flex flex-col overflow-hidden">
            <ChatSection 
              chatMessages={chatMessages}
              onSendMessage={handleSendMessage}
              onClearChat={handleClearChat}
            />
          </div>

          {/* Tools section */}
          <div className="w-1/3 bg-gray-50 rounded-lg p-4 flex flex-col overflow-hidden">
            <ToolsSection 
              notes={notes}
              flashcards={flashcards}
              onAddNote={handleAddNote}
              onDeleteNote={handleDeleteNote}
            />
          </div>
        </div>

        {/* Mobile version - tabbed interface */}
        <div className="flex flex-col h-full sm:hidden bg-gray-50 rounded-lg overflow-hidden">
          {/* Tab navigation */}
          <div className="flex border-b border-gray-200">
            <button
              onClick={() => setActiveMobileTab('chat')}
              className={`flex-1 py-3 hover:bg-gray-200 hover:cursor-pointer text-center ${
                activeMobileTab === 'chat'
                  ? 'border-b-2 border-blue-500 text-blue-500 font-medium'
                  : 'text-gray-500'
              }`}
            >
              Chat
            </button>
            <button
              onClick={() => setActiveMobileTab('sources')}
              className={`flex-1 py-3 hover:bg-gray-200 hover:cursor-pointer text-center ${
                activeMobileTab === 'sources'
                  ? 'border-b-2 border-blue-500 text-blue-500 font-medium'
                  : 'text-gray-500'
              }`}
            >
              Sources {sources.length > 0 && `(${sources.length})`}
            </button>
            <button
              onClick={() => setActiveMobileTab('tools')}
              className={`flex-1 hover:bg-gray-200 hover:cursor-pointer py-3 text-center ${
                activeMobileTab === 'tools'
                  ? 'border-b-2 border-blue-500 text-blue-500 font-medium'
                  : 'text-gray-500'
              }`}
            >
              Tools
            </button>
          </div>
          
          {/* Content area */}
          <div className="flex-1 p-4 flex flex-col overflow-hidden">
            {renderMobileContent()}
          </div>
        </div>
      </div>

      {/* Upload modal */}
      {uploadModalOpen && (
        <UploadModal
        onClose={() => setUploadModalOpen(false)}
        userId={currentUserId}
        selectedCourseId={courseId}
        onAddUrl={handleAddUrl}
        onAddTextSource={handleAddTextSource}
        onUploadSuccess={(uploadedFile) => {
          setSources((prev) => [...prev, {
            id: uploadedFile.id,
            name: uploadedFile.fileName,
            type: 'pdf'
          }]);
        }}
      />
      )}
    </div>
  );
};

export default CoursePage;