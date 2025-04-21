import React from 'react'
import { useKeenSlider } from 'keen-slider/react'
import 'keen-slider/keen-slider.min.css'
import { LuUpload } from "react-icons/lu";
import { FaCode } from "react-icons/fa";
import { TbPrompt } from "react-icons/tb";
import { RiCameraLensAiLine } from "react-icons/ri";
import { FaBookOpen } from "react-icons/fa";
import { FaVideo } from "react-icons/fa6";



const CarouselTools = () => {
  const [sliderRef] = useKeenSlider({
    slides: {
      perView: 1,
      spacing: 15,
    },
    breakpoints: {
      "(min-width: 640px)": {
        slides: { perView: 2, spacing: 15 },
      },
      "(min-width: 1024px)": {
        slides: { perView: 3, spacing: 30 },
      },
    },
    loop: true,
  })

  const cards = [
    {
      title: "Upload & Learn",
      desc: "Attach PDFs, Word docs, and notes—let the AI extract key insights.",
      icon: <LuUpload />,
    },
    {
      title: "Code with AI",
      desc: "Struggling with a coding problem? Let the AI help.",
      icon: <FaCode />,
    },
    {
      title: "Ask Anything",
      desc: "Need help understanding a concept? Just ask.",
      icon: <TbPrompt />,
    },
    {
      title: "Scan & Process",
      desc: "Upload images with text, let AI OCR and explain.",
      icon: <RiCameraLensAiLine />,
    },
    {
      title: "Smart Study Tools",
      desc: "Flashcards, tests, and quizzes auto-generated.",
      icon: <FaBookOpen />,
    },
    { 
      title: "Video Learning Made Easy",
      desc: "Upload videos, get AI summaries and key points.",
      icon: <FaVideo />,
    },
  ]

  return (
    <div className="px-4 py-12 rounded-2xl">
      <div ref={sliderRef} className="keen-slider">
        {cards.map((card, index) => (
          <div
            key={index}
            className="keen-slider__slide flex flex-col justify-between rounded-2xl shadow-md transition-transform hover:scale-[1.04] bg-white p-6 border border-gray-100 hover:shadow-xl duration-200 hover:cursor-pointer"
          >
            <div className="text-[#2D84FF] text-4xl mb-4">{card.icon}</div>
            <h3 className="font-bold text-lg text-gray-800 mb-2">{card.title}</h3>
            <p className="text-gray-600 text-sm leading-relaxed">{card.desc}</p>
          </div>
        ))}
      </div>
    </div>
  )
}

export default CarouselTools