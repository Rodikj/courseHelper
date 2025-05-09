import React from "react"
import { useState } from "react";
import { IoEyeOffOutline } from "react-icons/io5";
import { IoEyeOutline } from "react-icons/io5";
import { NavLink } from "react-router-dom";


const Register = () => {
    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  return (

    <section className="min-h-screen flex items-center justify-center bg-gray-100">

      <div className="flex flex-col md:flex-row bg-white rounded-lg shadow-lg overflow-hidden w-full max-w-5xl">

        <div className="md:w-1/2 items-center justify-center bg-[#2D84FF] p-10">
          <h1 className="text-4xl font-bold my-auto md:mt-25 mb-10 text-center">
            <span className="text-white">Course Helper </span>
          </h1>
          <h1 className="text-4xl font-bold text-white text-center">
            <span className="text-white">Superpower </span><span className="text-[#2C2C2C]">your learning with <span className="border-b-4 border-dotted border-white">AI</span></span>
          </h1>
        </div>

        <div className="md:w-1/2 p-10 ">
          <h2 className="text-3xl font-bold mb-6 text-[#2C2C2C]">Create Account</h2>
          <form className="space-y-4">
            <div className="flex space-x-4">
              <input
                type="text"
                placeholder="First Name"
                className="w-1/2 border border-gray-300 rounded-md p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"/>
              <input
                type="text"
                placeholder="Last Name"
                className="w-1/2 border border-gray-300 rounded-md p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"/>
            </div>
            <input
              type="email"
              placeholder="Email"
              className="w-full border border-gray-300 rounded-md p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"/>
            <div className="relative">
              <input
                type={showPassword ? "text" : "password"}
                placeholder="Password"
                className="w-full border border-gray-300 rounded-md p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"
              />
              <button
                type="button"
                onClick={() => setShowPassword(!showPassword)}
                className="absolute right-3 top-3 text-gray-600 hover:cursor-pointer">
                {showPassword ? 
                
                 <div className="text-[24px] font-bold"><IoEyeOutline /> </div>: <div className="text-[24px] font-bold"><IoEyeOffOutline /></div>}
              </button>
            </div>
            <div className="relative">
              <input
                type={showConfirmPassword ? "text" : "password"}
                placeholder="Confirm Password"
                className="w-full border border-gray-300 rounded-md p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"/>
              <button
                type="button"
                onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                className="absolute right-3 top-3 text-gray-600 hover:cursor-pointer">
                {showConfirmPassword ? 
        
                  <div className="text-[24px] font-bold"><IoEyeOutline /> </div> :<div className="text-[24px] font-bold"><IoEyeOffOutline /></div>}
              </button>
            </div>
            <button
              type="submit"
              // onClick={()=>handleSubmit()} - za podocna
              className="w-full bg-[#2D84FF] text-white font-semibold p-3 rounded-md hover:bg-blue-600 transition duration-300 hover:cursor-pointer">
              Register
            </button>
            <p className="text-sm text-[#7e7e7e]">
              Already have an account?{" "}
              <NavLink to="/login" className="text-blue-500 hover:underline">
                Log in
              </NavLink>
            </p>
          </form>
        </div>

      </div>
    </section>
  )
}

export default Register