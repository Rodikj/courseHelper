import {useState} from "react";
import LoginForm from "../components/LogIn/LoginForm.jsx";

const Login = () => {
    const [showPassword, setShowPassword] = useState(false);

    return (
        <div className="min-h-screen w-full flex items-center justify-center bg-blue-400">
            <div className="min-h-screen flex w-full h-full">
                {/* Left Side */}
                <div className="w-1/3  flex flex-col align-top p-8">
                    <h2 className="text-5xl font-bold text-black mb-2 mt-10 text-balance p-5">
                        <span className="text-white font-bold">Superpower </span>your learning with AI</h2>
                </div>

                {/* Right Side */}
                <div className="w-2/3 bg-white flex flex-col items-center pt-10 pb-10 rounded-s-4xl">
                    <div className="w-full max-w-xl lg:w-1/2 sm:w-1/2 mr-10">

                        <LoginForm />

                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login