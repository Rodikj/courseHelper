import React, { useState } from 'react';

const LoginForm = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [showPassword, setShowPassword] = useState(false);

    return (
        <form  className="space-y-4  align-middle">
            <h2 className="text-3xl font-bold text-gray-800 mt-10 pt-5 mb-10">Log in</h2>
            <div>
                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    className="w-full p-3 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                    required
                />
            </div>
            <div className="relative">
                <input
                    type={showPassword ? 'text' : 'password'}
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className="w-full p-3 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
                    required
                />
                <button
                    type="button"
                    onClick={() => setShowPassword(!showPassword)}
                    className="absolute inset-y-0 right-3 flex items-center text-gray-500"
                >
                    {showPassword ? 'Hide' : 'Show'}
                </button>
            </div>
            <button
                type="submit"
                className="w-full bg-blue-500 hover:bg-blue-600 text-white font-semibold p-3 rounded-md transition duration-300"
            >
                Log In
            </button>
            <p className="text-sm text-gray-600 ">
                Don't have an account?{' '}
                <a href="/register" className="text-blue-500 hover:underline">
                    Register now
                </a>
            </p>
        </form>
    );
};

export default LoginForm;
