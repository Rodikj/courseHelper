import React, { useState, useEffect } from 'react';
import { FaSignOutAlt, FaUser } from "react-icons/fa";
import { useNavigate } from 'react-router-dom';

const Header = () => {
  const [showProfileDropdown, setShowProfileDropdown] = useState(false);
  const [userInfo, setUserInfo] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    // Retrieve user info from localStorage
    // for actual implementation retrieve info from the database
    const storedUser = localStorage.getItem('registeredUser');
    if (storedUser) {
      setUserInfo(JSON.parse(storedUser));
    }
  }, []);

  const handleLogout = () => {
    // Remove authentication-related items from localStorage
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('username');
    localStorage.removeItem('registeredUser');
    
    // Redirect to login page
    navigate('/login');
  };

  return (
    <header className="bg-white shadow-sm py-4 px-6 flex justify-end items-center">
      <div className="relative">
        <button 
          onClick={() => setShowProfileDropdown(!showProfileDropdown)}
          className="flex hover:cursor-pointer hover:opacity-85 items-center space-x-2 focus:outline-none"
        >
          <div className="bg-blue-500 text-white rounded-full w-10 h-10 flex items-center justify-center">
            {userInfo ? userInfo.firstName.charAt(0).toUpperCase() : 'U'}
          </div>
          {/* <span className="text-gray-700 font-medium">
            {userInfo ? userInfo.firstName : 'User'}
          </span> */}
        </button>

        {showProfileDropdown && (
          <div className="absolute right-0 mt-2 w-64 bg-white border border-gray-200 rounded-lg shadow-lg z-50">
            {/* User Info Card */}
            {userInfo && (
              <div className="p-4 flex items-center space-x-4">
                <div className="bg-blue-500 text-white rounded-full w-12 h-12 flex items-center justify-center text-xl">
                  {userInfo.firstName.charAt(0).toUpperCase()}
                </div>
                <div>
                  <div className="font-semibold text-gray-800">
                    {userInfo.firstName} {userInfo.lastName}
                  </div>
                  <div className="text-sm text-gray-500">
                    {userInfo.email}
                  </div>
                </div>
              </div>
            )}

            {/* Logout Option */}
            <div className="py-1">
              <button 
                onClick={handleLogout}
                className="flex hover:cursor-pointer items-center w-full px-4 py-2 text-left hover:bg-gray-100"
              >
                <FaSignOutAlt className="mr-2" /> Logout
              </button>
            </div>
          </div>
        )}
      </div>
    </header>
  );
};

export default Header;