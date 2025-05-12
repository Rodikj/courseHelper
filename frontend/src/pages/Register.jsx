import React from "react"
import RegisterForm from "../components/Register/RegisterForm";


const Register = () => {

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
          <RegisterForm />
        </div>

      </div>
    </section>
  )
}

export default Register