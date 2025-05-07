import React, { useState } from 'react';
import { NavLink, useNavigate, useLocation } from 'react-router-dom';
import { FaBars, FaTimes } from 'react-icons/fa';

const Navbar = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const hideNavbar = location.pathname === '/register' || location.pathname === '/login';

  const [navOpen, setNavOpen] = useState(false);

  if (hideNavbar) {
    return null;
  }

  return (
    <nav className="sticky top-0 z-50 w-full bg-white shadow-md border-b border-[#9C9C9C]">
      <div className="flex justify-between items-center px-6 md:px-20 py-5">
        <h2 className="text-xl md:text-2xl text-[#2D84FF] font-bold">COURSE HELPER</h2>
        <div className="md:hidden">
          <button className='hover:cursor-pointer' onClick={() => setNavOpen(!navOpen)}>
            {navOpen ? <FaTimes size={24} /> : <FaBars size={24} />}
          </button>
        </div>

        {/* Desktop View links */}
        <ul className="hidden md:flex space-x-5 items-center">
          <NavLink to="/"><li className="pr-1.5">Home</li></NavLink>
          <NavLink to="/login">
            <li>
              <button className="text-gray-900 bg-[#2c2c2c] border border-gray-300 hover:bg-gray-100 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-gray-200 dark:text-[#2c2c2c] dark:border-gray-600 cursor-pointer">
                Login
              </button>
            </li>
          </NavLink>
          <NavLink to="/register">
            <li>
              <button className="text-gray-900 bg-white border border-gray-300 hover:bg-gray-100 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 cursor-pointer">
                Register
              </button>
            </li>
          </NavLink>
        </ul>
      </div>

      {/* Mobile view links */}
      {navOpen && (
        <div className="md:hidden flex flex-col items-center bg-white px-4 py-4 space-y-4 border-t border-gray-200">

          <div className='text-xl hover:text-[#2D84FF]'><NavLink to="/" onClick={() => setNavOpen(false)}>Home</NavLink></div>

          <button
            onClick={() => {
              navigate('/login');
              setNavOpen(false);
            }}
            className="text-black hover:text-[#2D84FF] hover:cursor-pointer font-medium rounded-lg text-lg px-5 py-2.5">
            Login
          </button>

          <button onClick={() => {navigate('/register');
              setNavOpen(false);
            }}
            className=" hover:cursor-pointer hover:text-[#2D84FF] font-medium rounded-lg text-lg px-5 py-2.5">
            Register
          </button>
        </div>
      )}
    </nav>
  );
};

export default Navbar;