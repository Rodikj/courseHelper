import React, { useState, useEffect } from 'react';
import { FaSignOutAlt } from "react-icons/fa";
import { useNavigate } from 'react-router-dom';

const Header = () => {
  const [showProfileDropdown, setShowProfileDropdown] = useState(false);
  const [userInfo, setUserInfo] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    // Retrieve user info from localStorage
    const storedUser = localStorage.getItem('registeredUser');
    if (storedUser) {
      setUserInfo(JSON.parse(storedUser));
    }
    
    // Also check for individual fields in case they're stored separately
    const fullName = localStorage.getItem('fullName');
    const email = localStorage.getItem('email');
    const name = localStorage.getItem('name');
    const surname = localStorage.getItem('surname');
    
    if (!storedUser && (fullName || email || name || surname)) {
      // If registeredUser isn't available but individual fields are,
      // create a user object from them
      setUserInfo({
        name: name || fullName || "",
        surname: surname || "",
        email: email || ""
      });
    }
  }, []);

  const handleLogout = () => {
    // Remove authentication-related items from localStorage
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('userId');
    localStorage.removeItem('email');
    localStorage.removeItem('fullName');
    localStorage.removeItem('name');
    localStorage.removeItem('surname');
    localStorage.removeItem('registeredUser');
    
    // Redirect to login page
    navigate('/login');
  };

  // Get initials for the avatar
  const getInitials = () => {
    if (userInfo?.name) {
      return userInfo.name.charAt(0).toUpperCase();
    } else if (userInfo?.firstName) {
      return userInfo.firstName.charAt(0).toUpperCase();
    } else if (userInfo?.fullName) {
      return userInfo.fullName.charAt(0).toUpperCase();
    }
    return "U";
  };

  // Get display name with proper capitalization
  const getDisplayName = () => {
    let displayName = "User";
    
    if (userInfo?.name && userInfo?.surname) {
      // Capitalize first letters of both name and surname
      const capitalizedName = userInfo.name.charAt(0).toUpperCase() + userInfo.name.slice(1);
      const capitalizedSurname = userInfo.surname.charAt(0).toUpperCase() + userInfo.surname.slice(1);
      displayName = `${capitalizedName} ${capitalizedSurname}`;
    } else if (userInfo?.firstName && userInfo?.lastName) {
      displayName = `${userInfo.firstName} ${userInfo.lastName}`;
    } else if (userInfo?.fullName) {
      displayName = userInfo.fullName;
    } else if (userInfo?.name) {
      displayName = userInfo.name.charAt(0).toUpperCase() + userInfo.name.slice(1);
    }
    
    return displayName;
  };

  return (
    <header className="bg-white shadow-sm py-3 md:py-4 px-4 md:px-6 flex justify-between md:justify-end items-center w-full">
      {/* Mobile menu button - not yet functional */}
      {/* <div className="md:hidden">
        <button className="text-gray-500 hover:text-gray-700">
          <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
          </svg>
        </button>
      </div> */}

      {/* Title visible only on mobile */}
      <div className="md:hidden text-xl font-bold text-blue-500">
        Course Helper
      </div>

      {/* User profile */}
      <div className="relative">
        <button
          onClick={() => setShowProfileDropdown(!showProfileDropdown)}
          className="flex hover:cursor-pointer hover:opacity-85 items-center space-x-2 focus:outline-none"
        >
          <div className="bg-blue-500 text-white rounded-full w-8 h-8 md:w-10 md:h-10 flex items-center justify-center">
            {getInitials()}
          </div>
        </button>

        {showProfileDropdown && (
          <div className="absolute right-0 mt-2 w-64 bg-white border border-gray-200 rounded-lg shadow-lg z-50">
            {/* User Info Card */}
            {userInfo && (
              <div className="p-4 flex items-center space-x-4">
                <div className="bg-blue-500 text-white rounded-full w-12 h-12 flex items-center justify-center text-xl">
                  {getInitials()}
                </div>
                <div className="overflow-hidden">
                  <div className="font-semibold text-gray-800 truncate">
                    {getDisplayName()}
                  </div>
                  <div className="text-sm text-gray-500 truncate">
                    {userInfo.email || ""}
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