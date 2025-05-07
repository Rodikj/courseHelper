import React from 'react'
import { useState } from 'react'
import { MdKeyboardArrowDown, MdKeyboardArrowUp } from "react-icons/md";

const Accordion = ({question, answer}) => {

  const [accOpen, setAccOpen] = useState(false);

  return (
    <div className='pb-5 border-b-1 border-gray-200'>
      <button onClick={()=>setAccOpen(!accOpen)} className='flex justify-between w-full hover: cursor-pointer'>
        <span className='text-xl md:text-2xl'>{question}</span>
        {accOpen ? <span><MdKeyboardArrowUp /></span> : <span><MdKeyboardArrowDown /></span> }
      </button>
      <div className={`grid overflow-hidden transition-all duration-300 ease-in-out text-slate-600 text-sm ${
        accOpen ? 'grid-rows-[1fr] opacity-100' : 'grid-rows-[0fr] opacity-0'
      }`}>
        <div className='overflow-hidden md:w-full pt-5 text-[18px]'>
          {answer}
        </div>
      </div>
    </div>
  )
}

export default Accordion