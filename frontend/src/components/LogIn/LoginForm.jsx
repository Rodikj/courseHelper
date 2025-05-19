import React, { useState } from 'react';
import { IoEyeOffOutline, IoEyeOutline } from "react-icons/io5";
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
            const response = await fetch("http://localhost:8080/api/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ email, password }),
                credentials: 'include' // allow session cookies (if backend uses them)
            });

            if (response.ok) {
                const data = await response.json();

                // Save user session info in localStorage (optional)
                localStorage.setItem("userId", data.id);
                localStorage.setItem("email", data.email);
                localStorage.setItem("isLoggedIn", "true");
                localStorage.setItem("fullName", data.fullName || '');

                localStorage.setItem("registeredUser", JSON.stringify({
                    firstName: data.name,     // if your backend sends 'name'
                    lastName: "",             // or fetch surname if available
                    email: data.email,
                  }));

                // Redirect to dashboard
                navigate("/dashboard");
            } else {
                const errorData = await response.json();
                setError(errorData.message || "Invalid email or password");
            }
        } catch (err) {
            console.error("Login error:", err);
            setError("An unexpected error occurred. Please try again.");
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