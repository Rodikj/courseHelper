import React, { useState } from 'react';
import { IoEyeOffOutline, IoEyeOutline } from "react-icons/io5";
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

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

        // Basic validations
        if (!email || !password) {
            setError('Please fill in all fields');
            return;
        }

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            setError('Please enter a valid email address');
            return;
        }

        setIsLoading(true);

        try {
            // Using axios instead of fetch for login
            const response = await axios.post(
                "http://localhost:8080/api/login", 
                { email, password },
                {
                    headers: {
                        "Content-Type": "application/json",
                    },
                    withCredentials: true // equivalent to credentials: 'include'
                }
            );

            // Axios automatically throws for error status codes, so if we get here, it was successful
            // Also, axios puts the response data directly in response.data
            const data = response.data;
                
            try {
                // Attempt to get more user data - ideally you'd have an endpoint for this
                // For now, we'll check if there's a registeredUser with the same email
                const storedUser = localStorage.getItem('registeredUser');
                let userData = null;
                
                if (storedUser) {
                    const parsedUser = JSON.parse(storedUser);
                    // If this is the same user that was stored before
                    if (parsedUser.email === email) {
                        userData = parsedUser;
                    }
                }
                
                // If we couldn't get stored user data, create minimal object
                if (!userData) {
                    // Extract a better name from email (capitalize first letter)
                    const emailName = email.split('@')[0];
                    const capitalizedName = emailName.charAt(0).toUpperCase() + emailName.slice(1);
                    
                    userData = {
                        id: data.id,
                        email: data.email,
                        name: capitalizedName,
                        surname: ''
                    };
                }

                // Save user data in localStorage
                localStorage.setItem("userId", data.id);
                localStorage.setItem("email", data.email);
                localStorage.setItem("name", userData.name);
                localStorage.setItem("surname", userData.surname);
                localStorage.setItem("isLoggedIn", "true");
                
                // Store the user object for Header to use
                localStorage.setItem("registeredUser", JSON.stringify(userData));

                // Redirect to dashboard
                navigate("/dashboard");
            } catch (fetchError) {
                console.error("Error fetching user details:", fetchError);
                // Still save basic login info and proceed
                localStorage.setItem("userId", data.id);
                localStorage.setItem("email", data.email);
                localStorage.setItem("isLoggedIn", "true");
                navigate("/dashboard");
            }
        } catch (err) {
            console.error("Login error:", err);
            // Axios errors contain response data in err.response.data
            if (err.response && err.response.data) {
                setError(err.response.data.message || "Invalid email or password");
            } else {
                setError("An unexpected error occurred. Please try again.");
            }
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-4">
            <h2 className="text-3xl font-bold text-[#2C2C2C] mb-6">Log in</h2>

            {error && (
                <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
                    {error}
                </div>
            )}

            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                required
            />

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
                    className="absolute inset-y-0 right-3 flex items-center text-gray-600"
                >
                    {showPassword
                        ? <IoEyeOutline className="text-xl" />
                        : <IoEyeOffOutline className="text-xl" />}
                </button>
            </div>

            <button
                type="submit"
                disabled={isLoading}
                className={`w-full bg-[#2D84FF] text-white font-semibold p-3 rounded-md transition duration-300 ${isLoading ? 'opacity-70 cursor-not-allowed' : 'hover:bg-blue-600'}`}
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