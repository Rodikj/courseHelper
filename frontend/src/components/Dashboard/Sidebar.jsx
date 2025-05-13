import React from 'react';
import { Link } from 'react-router-dom';

const Sidebar = () => {
  return (
    <aside className="w-64 bg-blue-500 min-h-screen shadow-md border-r-1 border-gray-300 p-6 hidden md:block fixed left-0 top-0 z-20">
      <Link to="/">
        <h2 className="text-2xl font-bold mb-10 text-white">Course Helper</h2>
      </Link>
      <nav className="flex flex-col gap-2 text-white">
        <p className="hover:cursor-default">Dashboard</p>
        <a href="#" className="hover:bg-blue-400 rounded-md">My Courses</a>
        <a href="#" className="hover:bg-blue-400 rounded-md mt-10">Settings</a>
      </nav>
    </aside>
  );
};

export default Sidebar;