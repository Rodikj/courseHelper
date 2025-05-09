import React, { useState } from 'react';
import { IoEyeOffOutline } from "react-icons/io5";
import { IoEyeOutline } from "react-icons/io5";

const LoginForm = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false);

    return (
        <form  className="space-y-4">
            <h2 className="text-3xl font-bold text-[#2C2C2C] mb-6">Log in</h2>
            <div>
                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    className="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                    required
                />
            </div>
            <div className="relative">
                <input
                    type={showPassword ? 'text' : 'password'}
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                    required
                />
                <button
                    type="button"
                    onClick={() => setShowPassword(!showPassword)}
                    className="absolute inset-y-0 right-3 flex items-center text-gray-600 hover:cursor-pointer"
                >
                    {showPassword ? <div className="text-[24px] font-bold"><IoEyeOutline /> </div>: <div className="text-[24px] font-bold"><IoEyeOffOutline /></div>}
                </button>
            </div>
            <button
                type="submit"
                className="w-full bg-[#2D84FF] hover:bg-blue-600 text-white font-semibold p-3 rounded-md transition duration-300 hover:cursor-pointer"
            >
                Log In
            </button>
            <p className="text-sm text-[#7e7e7e] ">
                Don't have an account?{' '}
                <a href="/register" className="text-blue-500 hover:underline">
                    Register now
                </a>
            </p>
        </form>
    );
};

export default LoginForm;
