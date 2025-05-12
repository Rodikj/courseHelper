import React, { useState } from 'react';
import { IoEyeOffOutline } from "react-icons/io5";
import { IoEyeOutline } from "react-icons/io5";
import Dashboard from '../../pages/Dashboard';
import { useNavigate } from 'react-router-dom';


const LoginForm = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false);
    const [error, setError] = useState('');
    const [isLoading, setIsLoading] = useState(false);

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        
        // Basic validation
        if (!email || !password) {
            setError('Please fill in all fields');
            return;
        }

        // Email format validation
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            setError('Please enter a valid email address');
            return;
        }

        setIsLoading(true);

        try {

            // Retrieve registered user from localStorage
            const registeredUser = localStorage.getItem('registeredUser');

            // Here you would normally call your authentication API
            // For example:
            // const response = await loginUser(email, password);
            
            // For demonstration, simulating an API call with a timeout
            // Replace this with your actual authentication logic
                // Mock credentials check - replace with actual authentication
                if (registeredUser) {
                  const user = JSON.parse(registeredUser);

                  // Check if email and password match
                  if (user.email === email && user.password === password) {
                    // Store authentication token or user data in localStorage
                    localStorage.setItem("isLoggedIn", "true");

                    // Store username for use in dashboard
                    localStorage.setItem("username", user.firstName);

                    // Redirect to dashboard
                    navigate("/dashboard");
                  } else {
                    setError("Invalid email or password");
                    setIsLoading(false);
                  }
                } else {
                  setError("No registered user found. Please register first.");
                  setIsLoading(false);
                }
        } catch (error) {
            setError('Login failed. Please try again.');
            setIsLoading(false);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-4">
            <h2 className="text-3xl font-bold text-[#2C2C2C] mb-6">Log in</h2>
            
            {error && (
                <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
                    <span className="block sm:inline">{error}</span>
                </div>
            )}
            
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
                    {showPassword ? 
                        <div className="text-[24px] font-bold"><IoEyeOutline /></div> : 
                        <div className="text-[24px] font-bold"><IoEyeOffOutline /></div>
                    }
                </button>
            </div>
            
            <button 
                type="submit"
                disabled={isLoading}
                className={`w-full bg-[#2D84FF] hover:bg-blue-600 text-white font-semibold p-3 rounded-md transition duration-300 ${isLoading ? 'opacity-70 cursor-not-allowed' : 'hover:cursor-pointer'}`}
            >
                {isLoading ? 'Logging in...' : 'Log In'}
            </button>
            
            <p className="text-sm text-[#7e7e7e]">
                Don't have an account?{' '}
                <a href="/register" className="text-blue-500 hover:underline">
                    Register now
                </a>
            </p>
        </form>
    );
};

export default LoginForm;