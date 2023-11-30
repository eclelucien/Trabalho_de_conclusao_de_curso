

import React from 'react';
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import Login from './components/Login/Login';
import Home from './components/Home';
import CreateUser from './components/CreateUser/CreateUser'
import { useAuth } from './context/AuthContext';



function RoutesApp() {
    const categories = ['Electronics', 'Clothing', 'Home Decor', 'Beauty', 'Books'];
    const { user } = useAuth();
    return (
        <BrowserRouter>
            <Routes>
                <Route
                    path="/"
                    element={user ? <Home categories={categories} /> : <Navigate to="/login" />}
                />
                <Route path="/login" element={user ? <Navigate to="/" /> : <Login />} />
                <Route path='/create-user' element={<CreateUser />} />
            </Routes>
        </BrowserRouter>
    )
}
export default RoutesApp;