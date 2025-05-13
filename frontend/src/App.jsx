import { useState } from 'react'
import './App.css'
import { Route, createBrowserRouter, createRoutesFromElements, RouterProvider } from 'react-router-dom'
import Home from './pages/Home'
import Register from './pages/Register'
import Login from './pages/Login'
import RootLayout from './layout/RootLayout'
import Dashboard from './pages/Dashboard'
import CoursePage from './pages/CoursePage'
import ProtectedRoute from './components/ProtectedRoute/ProtectedRoute'


function App() {
  const router = createBrowserRouter(createRoutesFromElements(
    <>
      <Route path='/' element={<RootLayout />}>
        <Route index element={<Home />}/>
        <Route path='register' element={<Register />} />
        <Route path='login' element={<Login />} />
      </Route>

      <Route path='dashboard' element={
      <ProtectedRoute>
        <Dashboard />
      </ProtectedRoute>
        } />

      <Route path='course' element={
      <ProtectedRoute>
        <CoursePage />
      </ProtectedRoute>
        } />
    </>
  ))

  return (
    <RouterProvider router={router} />
 )
}

export default App
