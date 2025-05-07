import Navbar from "../components/Navbar/Navbar"
import { Route } from "react-router-dom"
import Register from "./Register"
import Login from "./Login"
import robot from "../assets/robot.png"
import books from "../assets/books.png"
import { useNavigate } from "react-router-dom"
import { useState } from "react"
import CarouselTools from "../components/CarouselTools/CarouselTools"
import FAQ from "../components/FAQs/FAQ"
import { IoMdCheckmarkCircleOutline } from "react-icons/io";
import { FaInstagram } from "react-icons/fa";
import { LiaFacebook } from "react-icons/lia";
import { CiLinkedin } from "react-icons/ci";
import VectorWaves from "../assets/VectorWaves.png"


const Home = () => {
  const navigate = useNavigate();
  const [navOpen, setNavOpen] = useState(false);

  return (
  <>
    <section className="mx-6 md:mx-20 xl:min-h-screen md:max-w-screen-x pb-10">

      <div className="hidden xl:flex justify-end"><img src={books} alt="img.png" className="absolute mt-120 mr-35" /></div>
      <div className="hidden xl:flex justify-end"><img src={robot} alt="hero.png" className="absolute mr-30"/></div>

      <div className="mt-10 md:mt-30">
        <h1 className="text-[38px] text-center font-bold leading-10 md:text-left md:text-[64px] md:leading-18"><span className="text-[#2D84FF]">Superpower </span> your <br /> learning with AI</h1>
        <div className="mt-3 text-center md:text-left md:w-103">
          <p className="text-[12px] md:text-[18px] text-gray-500 font-semibold">Leverage cutting-edge technology to tackle university subjects effortlessly. Experience how our AI-driven platform enhances your study routine by prompting insightful questions and extracting text from images.</p>
        </div>
        <div className="mt-5 flex justify-center md:justify-start"><button className=" text-gray-900 bg-white border border-gray-300 hover:bg-gray-100 font-medium rounded-3xl text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700 w-[220px] h-[48px] hover: cursor-pointer" onClick={()=>navigate('/register')}>Try now for free</button></div>
      </div>
    </section>

    <section className="bg-[#2c2c2c] md:mt-20">
      <div className="mx-20 flex justify-center pt-5">
        <h1 className="text-[32px] leading-10 md:text-[48px] md:leading-18 text-white font-bold w-full md:w-[600px] text-center mt-5"><span className="text-[#2D84FF]">10X</span> your learning and productivity with our tools.</h1>
      </div>
      <div className="mx-10 md:mx-20 mt-10 pb-20">
        <CarouselTools />
      </div>  
    </section>

    <section className="mt-20">
      <div className="mx-20 flex justify-center">
        <h1 className=" text-[32px] md:text-[48px] text-[#000000] leading-10 font-semibold md:leading-18 w-[650px] text-center">Frequently Asked <span className="text-[#2D84FF]">Questions</span></h1>
      </div>
      <div className="pb-20 pt-10 md:h-[600px]">
        <FAQ />
      </div>
    </section>

    <section>
      <div className="text-center max-w-[780px] mx-6 md:mx-auto mt-10">
        <h1 className="text-[32px] leading-10 md:text-[48px] text-black font-bold md:leading-tight">
          Maximize Your Academic Performance with <span className="text-[#2D84FF]">Course Helper AI</span>
        </h1>
      </div>
      
      <div className="text-center mb-16">
        <p className="mx-6 text-[16px] md:text-[20px] text-gray-400 font-semibold">
          Transform your study habits and elevate your learning journey
        </p>
      </div>

      {/* Cards Container */}
      <div className="flex md:mx-10 flex-col xl:flex-row justify-center items-center gap-8 relative px-6">

        {/* BasicSupport */}
        <div className="bg-white shadow-2xl h-full w-full rounded-2xl p-6 flex flex-col justify-between">
          <div className="mx-5">
            <div className="flex justify-center pt-5">
              <h1 className="text-4xl font-semibold">Basic Support</h1>
            </div>
              <div className="pt-5 text-center">
                <p>Get started with our essential tools and enjoy a trial for just $0.00, perfect for enhancing your efficiency in studies.</p>
              </div>
              <div className="text-center">
                <h3 className="text-[64px] font-bold text-[#2D84FF] mb-2">$0</h3>
              </div>
            <p className="text-sm text-gray-600 mb-4 text-center">14-DAY FREE TRIAL</p>
            <div className="flex justify-center">
              <button onClick={()=>navigate('/register')} className="mb-6 bg-[#ffffff] text-black rounded-lg py-3 font-semibold border-1 p-4 hover: cursor-pointer hover:bg-[#2D84FF] hover:text-white">
              SELECT THIS PLAN
              </button>
            </div>
            <ul className="space-y-2 text-sm text-gray-600">
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div>AI-Driven Prompts</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> Text Extraction</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> Create Flashcards</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> Engaging Quizlets</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> Multimedia Support</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div>Project A/B Testing</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div>Study Material Organization</li>
             </ul>
          </div>
        </div>
        {/* Advanced Assistance*/}
        <div className="relative w-full">
        {/* Badge */}
        <div className="absolute -top-10 left-1/2 transform -translate-x-1/2 z-10 mt-3">
          <div className="bg-[#2c2c2c] w-full h-full p-4 rounded-xl flex items-center justify-center">
            <p className="text-white font-semibold text-sm">MOST POPULAR</p>
          </div>
        </div>

        {/* Card */}
        <div className="bg-[#2D84FF] shadow-2xl h-full w-full rounded-2xl p-6 text-white flex flex-col justify-between pt-16">
          <div className="mx-5">
            <div className="flex justify-center">
              <h1 className="text-4xl font-semibold">Advanced <br /> Assistance</h1>
            </div>
          <div className="pt-5 text-center">
            <p>After your trial, continue enhancing your learning with our most comprehensive subscription.</p>
          </div>
          <h1 className="text-[64px] text-center font-bold mb-2">$20</h1>
          <p className="text-sm mb-4 text-center">MONTHLY SUBSCRIPTION</p>
          <div className="flex justify-center">
              <button onClick={()=>navigate('/register')} className=" hover:text-[#2D84FF] mb-6 bg-white text-black rounded-lg py-3 font-semibold border-1 p-4 hover: cursor-pointer">
              SELECT THIS PLAN
              </button>
          </div>
            <ul className="space-y-2 text-sm">
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> Enhanced User Access</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div>Flashcards & Quizlet Perks</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> Full Multimedia Support</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> Optimized Study Aids</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div>Comprehensive Resource Access</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> Priority Customer Support</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> Efficient Learning</li>
            </ul>
           </div>
          </div>
        </div>

        {/* Ultimate Access*/}
        <div className="bg-white shadow-2xl h-full w-full rounded-2xl p-6 flex flex-col justify-between">
          <div className="mx-5">
            <div className="flex justify-center pt-5">
              <h1 className="text-4xl font-semibold">Ultimate Access</h1>
            </div>
            <div className="pt-5 ">
              <p className="flex-wrap text-center">Unlock your full potential with our premium offering for targeted academic excellence.</p>
            </div>
            <h3 className="text-[64px] text-center font-bold text-[#2D84FF] mb-2">$59</h3>
            <p className="text-sm text-center text-gray-500 mb-4">MONTHLY SUBSCRIPTION</p>
            <div className="flex justify-center">
              <button onClick={()=>navigate('/register')} className="hover:bg-[#2D84FF] hover:text-white mb-6 bg-[#ffffff] text-black rounded-lg py-3 font-semibold border-1 p-4 hover: cursor-pointer">
              SELECT THIS PLAN
              </button>
            </div>
            <ul className="space-y-2 text-sm text-gray-600">
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> Unlimited AI Access</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> Unrestricted Flashcard Creation</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> All Subject Support</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> Comprehensive Multimedia Features</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> Advanced Study Techniques</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div>Full Saga of Educational Resources</li>
              <li><div className="inline-flex pr-2 "><IoMdCheckmarkCircleOutline /></div> Dedicated Tutoring Support</li>
            </ul>
          </div>
        </div>
      </div>
      <div><img src={VectorWaves} alt="Design" className="w-full" /></div>
    </section>

    {/* {Footer} */}
    <section className="bg-[#2c2c2c] h-[500px]">
      <div className="pt-20">
        <h1 className="text-center text-4xl text-white">Contact Us</h1>
      </div>
      <div className="flex justify-center pt-10 gap-10">
        <div className="pt-0.5">
          <FaInstagram className="text-white h-[42px] w-[42px]"/>
        </div>
       <LiaFacebook className="text-white h-[48px] w-[48px]" />
       <CiLinkedin className="text-white h-[48px] w-[48px]" />
      </div>
      <div className="pt-10 text-white flex gap-4 justify-center">
        <p>Home</p>
        <p>About Us</p>
        <p>Pricing</p>
        <p>FAQs</p>
      </div>
      <div className="pt-5 text-white flex gap-4 justify-center">
        <p onClick={()=>navigate('/login')} className="hover: cursor-pointer hover:underline">Login</p>
        <p onClick={()=>navigate('/register')} className="hover: cursor-pointer hover:underline">Register</p>
      </div>
      <div className="pt-25 text-white flex justify-center">
        <p>© 2025 Course Helper.AI All Rights Reserved</p>
      </div>
    </section>
  </>
  )
}

export default Home   