import React from 'react'
import { Link } from 'react-router-dom'
import Home from '../../pages/Home'

const Sidebar = () => {
  return (
    <div>
      <aside className="w-64 bg-blue-500 shadow-md border-r-1 border-gray-300 p-6 hidden md:block h-full">
        {/* <h2 className="text-2xl font-bold mb-10 text-white">Course Helper</h2> */}
        <Link to="/"><h2 className="text-2xl font-bold mb-10 text-white">Course Helper</h2></Link>
        <nav className="flex flex-col gap-2 text-white">
          <p className="hover:cursor-default">Dashboard</p>
          <a href="#" className=" hover:bg-blue-400 rounded-md">My Courses</a>
          <a href="#" className=" hover:bg-blue-400 rounded-md mt-10">Settings</a>
        </nav>
      </aside>
    </div>
  )
}

export default Sidebar