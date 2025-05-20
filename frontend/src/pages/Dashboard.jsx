import React, { useEffect, useState } from "react";
import Sidebar from "../components/Dashboard/Sidebar";
import Header from "../components/Dashboard/Header";
import { createCourse, getCourses } from "../components/API/api";
import { FaTrashAlt } from "react-icons/fa";

const Dashboard = () => {
  const [courses, setCourses] = useState([]);
  const [showCreateModal, setShowCreateModal] = useState(false);
  const [newCourseName, setNewCourseName] = useState('');

  useEffect(() => {
    getCourses().then(data => {
      console.log("Fetched courses:", data); // Debug: Log fetched courses
      setCourses(data);
    });
  }, []);

  const handleCreateCourse = async () => {
    const userId = localStorage.getItem("userId");
    if (!userId || !newCourseName.trim()) return;

    try {
      const newCourse = await createCourse(userId, newCourseName);
      console.log("Created course:", newCourse); // Debug: Log the newly created course
      
      // Ensure the new course has the correct property for name
      const formattedNewCourse = {
        ...newCourse,
        name: newCourse.name || newCourse.courseName || newCourseName // Fallback to ensure name exists
      };
      
      setCourses([...courses, formattedNewCourse]);
      setNewCourseName("");
      setShowCreateModal(false);
    } catch (err) {
      console.error("Course creation failed:", err.message);
    }
  };

  const handleOpenCourse = (courseId, courseName) => {
    window.location.href = `/course?id=${courseId}&name=${encodeURIComponent(courseName)}`;
  };

  const handleDeleteCourse = (courseId) => {
    const confirmed = window.confirm("Are you sure you want to delete this course?");
    if (!confirmed) return;

    const updatedCourses = courses.filter(course => course.id !== courseId);
    setCourses(updatedCourses);
    // TODO: Delete from backend (not implemented yet)
  };

  return (
    <div className="h-screen flex bg-white overflow-hidden">
      <Sidebar />

      <div className="flex-1 flex flex-col h-screen w-full md:ml-64">
        <div className="sticky top-0 z-10">
          <Header />
        </div>

        <div className="flex-1 overflow-y-auto p-4 md:p-6 bg-white">
          <div className="max-w-4xl mx-auto">
            <h2 className="text-2xl font-bold mb-6">My Courses</h2>

            <button
              className="flex items-center gap-2 bg-gray-100 text-black font-semibold hover:cursor-pointer px-5 py-3 rounded-full hover:bg-gray-200"
              onClick={() => setShowCreateModal(true)}
            >
              <span className="text-xl">＋</span> Create new
            </button>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4 md:gap-6 mt-8 md:mt-10">
              {courses.map((course, index) => {
                // Debug: Log each course object
                console.log(`Course ${index}:`, course);
                
                // Determine the course name with fallbacks
                const displayName = course.name || course.courseName || course.course_name || "Untitled Course";
                
                return (
                  <div
                    key={index}
                    className="bg-blue-500 hover:bg-blue-600 hover:cursor-pointer rounded-2xl p-4 md:p-6 shadow hover:shadow-xl transition"
                    onClick={() => handleOpenCourse(course.id, displayName)}
                  >
                    <div className="text-xl md:text-2xl text-white mb-2 font-medium truncate">
                      {displayName}
                    </div>
                    <div className="flex justify-end">
                      <button
                        onClick={(e) => {
                          e.stopPropagation();
                          handleDeleteCourse(course.id);
                        }}
                        className="flex justify-center items-center bg-gray-300 hover:bg-gray-500 text-white rounded-full w-6 h-6 text-xs"
                        title="Delete"
                      >
                        <FaTrashAlt />
                      </button>
                    </div>
                    <div className="text-sm text-gray-300">
                      {/* Optional: backend dateCreated */}
                      {course.createdAt && new Date(course.createdAt).toLocaleDateString()}
                    </div>
                  </div>
                );
              })}
            </div>
          </div>
        </div>
      </div>

      {showCreateModal && (
        <div className="fixed inset-0 bg-transparent backdrop-blur-sm bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white text-black p-4 md:p-6 rounded-xl shadow-lg w-11/12 md:w-full max-w-md mx-4">
            <h2 className="text-xl text-blue-500 font-semibold">Course Helper</h2>
            <h2 className="text-xl mb-4">New Course</h2>
            <input
              type="text"
              placeholder="Enter course name"
              className="w-full border border-gray-300 p-2 rounded mb-6 md:mb-10"
              value={newCourseName}
              onChange={(e) => setNewCourseName(e.target.value)}
              onKeyDown={(e) => e.key === "Enter" && handleCreateCourse()}
              autoFocus
            />
            <div className="flex justify-end gap-2">
              <button
                onClick={() => setShowCreateModal(false)}
                className="px-3 py-2 w-1/4 md:w-1/5 rounded-md bg-gray-300 text-black hover:bg-gray-400 hover:cursor-pointer"
              >
                Cancel
              </button>
              <button
                onClick={handleCreateCourse}
                className="px-3 py-2 w-1/4 md:w-1/5 rounded-md bg-blue-600 text-white hover:bg-blue-700 hover:cursor-pointer"
                disabled={!newCourseName.trim()}
              >
                Add
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Dashboard;