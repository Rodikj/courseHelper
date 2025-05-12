import React, { useState } from "react";
import { IoEyeOffOutline } from "react-icons/io5";
import { IoEyeOutline } from "react-icons/io5";
import { NavLink, useNavigate } from "react-router-dom";

const RegisterForm = () => {
  const [userFirstName, setUserFirstName] = useState("");
  const [userLastName, setUserLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const [error, setError] = useState('');
  const [successMessage, setSuccessMessage] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const navigate = useNavigate();

  const validateForm = () => {
    // Reset previous errors
    setError('');

    // Check if all fields are filled
    if (!userFirstName || !userLastName || !email || !password || !confirmPassword) {
      setError('Please fill in all fields');
      return false;
    }

    // Email validation
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      setError('Please enter a valid email address');
      return false;
    }

    // Password validation
    if (password !== confirmPassword) {
      setError('Passwords do not match');
      return false;
    }

    // Password strength (optional, but recommended)
    if (password.length < 8) {
      setError('Password must be at least 8 characters long');
      return false;
    }

    return true;
  };

  const save = async (e) => {
    e.preventDefault();
    
    // Validate form
    if (!validateForm()) {
      return;
    }

    setIsLoading(true);

        {/*Ovaa ne e vistinskiot api endpoint, videte tocno kako treba*/}
  //   try{
  //     await axios.post(
  //       "http://localhost:8085/api/user/save", {
  //         userFirstName : userFirstName,
  //         userLastName : userLastName,
  //         email : email,
  //         password : password,
  //       });
  //       alert ("User registration successfull");
  //   }catch(err){
  //     alert(err);
  //   }


    try {

      // Simulate registration process
      setTimeout(() => {
        // Create user object to store in localStorage
        const user = {
          firstName: userFirstName,
          lastName: userLastName,
          email: email,
          password: password // Note: In a real app, NEVER store passwords in plain text
        };

        // Store user details in localStorage
        localStorage.setItem('registeredUser', JSON.stringify(user));

        // Optional: Store isLoggedIn and username for dashboard
        localStorage.setItem('isLoggedIn', 'true');
        localStorage.setItem('username', userFirstName);

        // Show success message
        setSuccessMessage('Registration successful!');

        // Auto-close success message and navigate to login
        setTimeout(() => {
          setSuccessMessage('');
          navigate('/login');
        }, 1000);
      }, 1000);
    } catch (err) {
      setError('Registration failed. Please try again.');
      setIsLoading(false);
    }
  };

  return (
    <form onSubmit={save} className="space-y-4">
      {/* Success Message */}
      {successMessage && (
        <div className="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative" role="alert">
          <span className="block sm:inline">{successMessage}</span>
        </div>
      )}

      {/* Error Message */}
      {error && (
        <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
          <span className="block sm:inline">{error}</span>
        </div>
      )}

      <div className="flex space-x-4">
        <input
          type="text"
          placeholder="First Name"
          value={userFirstName}
          onChange={(e) => setUserFirstName(e.target.value)}
          className="w-1/2 border border-gray-300 rounded-md p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"
          required
        />
        <input
          type="text"
          placeholder="Last Name"
          value={userLastName}
          onChange={(e) => setUserLastName(e.target.value)}
          className="w-1/2 border border-gray-300 rounded-md p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"
          required
        />
      </div>
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        className="w-full border border-gray-300 rounded-md p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"
        required
      />
      <div className="relative">
        <input
          type={showPassword ? "text" : "password"}
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="w-full border border-gray-300 rounded-md p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"
          required
        />
        <button
          type="button"
          onClick={() => setShowPassword(!showPassword)}
          className="absolute right-3 top-3 text-gray-600 hover:cursor-pointer"
        >
          {showPassword ? (
            <div className="text-[24px] font-bold">
              <IoEyeOutline />{" "}
            </div>
          ) : (
            <div className="text-[24px] font-bold">
              <IoEyeOffOutline />
            </div>
          )}
        </button>
      </div>
      <div className="relative">
        <input
          type={showConfirmPassword ? "text" : "password"}
          placeholder="Confirm Password"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
          className="w-full border border-gray-300 rounded-md p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"
          required
        />
        <button
          type="button"
          onClick={() => setShowConfirmPassword(!showConfirmPassword)}
          className="absolute right-3 top-3 text-gray-600 hover:cursor-pointer"
        >
          {showConfirmPassword ? (
            <div className="text-[24px] font-bold">
              <IoEyeOutline />{" "}
            </div>
          ) : (
            <div className="text-[24px] font-bold">
              <IoEyeOffOutline />
            </div>
          )}
        </button>
      </div>
      <button
        type="submit"
        disabled={isLoading}
        className={`w-full bg-[#2D84FF] text-white font-semibold p-3 rounded-md hover:bg-blue-600 transition duration-300 ${isLoading ? 'opacity-70 cursor-not-allowed' : 'hover:cursor-pointer'}`}
      >
        {isLoading ? 'Registering...' : 'Register'}
      </button>
      <p className="text-sm text-[#7e7e7e]">
        Already have an account?{" "}
        <NavLink to="/login" className="text-blue-500 hover:underline">
          Log in
        </NavLink>
      </p>
    </form>
  );
};

export default RegisterForm;