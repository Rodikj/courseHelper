import React, { useEffect, useState } from "react";
import Sidebar from "../components/Dashboard/Sidebar";
import Header from "../components/Dashboard/Header";
import { FaTrashAlt, FaUser, FaSignOutAlt } from "react-icons/fa";

const Dashboard = () => {
  const [courses, setCourses] = useState([]);
  const [showCreateModal, setShowCreateModal] = useState(false);
  const [newCourseName, setNewCourseName] = useState('');
  const [username, setUsername] = useState('');
  const [showProfileDropdown, setShowProfileDropdown] = useState(false);

  useEffect(() => {
    // Load saved courses from localStorage
    const savedCourses = localStorage.getItem('courses');
    if (savedCourses) {
      setCourses(JSON.parse(savedCourses));
    }

    // Get username from localStorage
    const storedUsername = localStorage.getItem('username');
    if (storedUsername) {
      setUsername(storedUsername);
    }
  }, []);
  
  const handleCreateNewCourse = () => {
    if (!newCourseName.trim()) return;
    
    const newCourse = {
      id: Date.now(),
      name: newCourseName,
      dateCreated: new Date().toLocaleDateString()
    };
    
    const updatedCourses = [...courses, newCourse];
    setCourses(updatedCourses);
    localStorage.setItem('courses', JSON.stringify(updatedCourses));
    
    setNewCourseName('');
    setShowCreateModal(false);
  };
  
  const handleOpenCourse = (courseId, courseName) => {
    // Navigate to course page with ID and name
    window.location.href = `/course?id=${courseId}&name=${encodeURIComponent(courseName)}`;
  };
  const handleDeleteCourse = (courseId) => {
    const confirmed = window.confirm("Are you sure you want to delete this course?");
    if (!confirmed) return;
  
    const updatedCourses = courses.filter(course => course.id !== courseId);
    setCourses(updatedCourses);
    localStorage.setItem('courses', JSON.stringify(updatedCourses));
  };

  const handleLogout = () => {
    // Remove authentication-related items from localStorage
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    
    // Redirect to login page
    window.location.href = '/login';
  };


  return (
    <div className="min-h-screen flex bg-white">
      <Sidebar />

      {/* Main Content */}
      <div className="flex-1 flex flex-col">
        <Header />

        <main className="flex-1 p-6">
          <div className="max-w-4xl mx-auto">
            <h2 className="text-2xl font-bold mb-6">My Courses</h2>

            <button
              className="flex items-center gap-2 bg-gray-100 text-black font-semibold hover:cursor-pointer px-5 py-3 rounded-full hover:bg-gray-200"
              onClick={() => setShowCreateModal(true)}
            >
              <span className="text-xl">＋</span> Create new
            </button>

            {/* Modal */}
            {showCreateModal && (
              <div className="fixed inset-0 bg-transparent backdrop-blur-sm bg-opacity-50 flex items-center justify-center z-50">
                <div className="bg-white text-black p-6 rounded-xl shadow-lg w-full max-w-md">
                  <h2 className="text-xl text-blue-500 font-semibold">
                    Course Helper
                  </h2>
                  <h2 className="text-xl mb-4">New Course</h2>
                  <input
                    type="text"
                    placeholder="Enter course name"
                    className="w-full border border-gray-300 p-2 rounded mb-10"
                    value={newCourseName}
                    onChange={(e) => setNewCourseName(e.target.value)}
                    onKeyDown={(e) =>
                      e.key === "Enter" && handleCreateNewCourse()
                    }
                  />
                  {
                    <div className="flex justify-end gap-2">
                      <button
                        onClick={() => setShowCreateModal(false)}
                        className="px-4 py-2 w-1/5 rounded-md bg-gray-300 text-black hover:bg-gray-400 hover:cursor-pointer"
                      >
                        Cancel
                      </button>
                      <button
                        onClick={handleCreateNewCourse}
                        className="px-4 py-2 w-1/5 rounded-md bg-blue-600 text-white hover:bg-blue-700 hover:cursor-pointer"
                      >
                        Add
                      </button>
                    </div>
                  }
                </div>
              </div>
            )}

            <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mt-10">
              {courses.map((course, index) => (
                <div
                  key={index}
                  className="bg-blue-500 hover:bg-blue-600 hover:cursor-pointer rounded-2xl p-6 shadow hover:shadow-xl transition"
                >
                  <div
                    className="text-2xl text-white mb-2 font-medium truncate"
                    onClick={() => handleOpenCourse(course.id, course.name)}
                  >
                    {course.name}
                  </div>
                  <div className="flex justify-end">
                    <button
                      onClick={(e) => {
                        e.stopPropagation(); // Prevent navigating when deleting
                        handleDeleteCourse(course.id);
                      }}
                      className="flex justify-center items-center bg-gray-300 hover:cursor-pointer hover:bg-gray-500 text-white rounded-full w-6 h-6 text-xs"
                      title="Delete"
                    >
                      <div className="text-end justify-end items-end">
                        <FaTrashAlt />
                      </div>
                    </button>
                  </div>
                  <div className="text-sm text-gray-300">
                    {course.dateCreated}
                  </div>
                </div>
              ))}
            </div>
          </div>
        </main>
      </div>
    </div>
  );
}

export default Dashboard;