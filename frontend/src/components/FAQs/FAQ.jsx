import React from 'react'
import Accordion from './Accordion'

const FAQ = () => {
  return (
    <div className='mx-6 md:mx-20 my-10'>
      <Accordion question='What is Course Helper AI?'   
      answer='Course Helper AI is an AI-powered educational platform designed to help students study smarter. It offers features like flashcard generation, text extraction from images, personalized quizlets, and multimedia learning tools—all powered by intelligent algorithms.' />

      <Accordion question='How does the AI help with learning?' 
      answer='The AI processes your uploaded files, extracts key concepts, generates summaries, and answers any questions you have. It also creates quizzes and flashcards to reinforce your knowledge.' />

      <Accordion question='What file types can I upload?'   
      answer='You can upload:
      •	Documents: PDFs, Word (.docx)
      •	Videos: MP4, AVI, MOV
      •	Images: JPG, PNG (for OCR text extraction)' />

      <Accordion question='Is my uploaded content secure?'   
      answer='We prioritize data privacy and security. Your files are processed securely, and we do not store or share your personal data.' />

    </div>
  )
}

export default FAQ  