

import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
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
                <Route path="/" element={<Home categories={categories} />} />
                <Route path="/login" element={<Login />} />
                <Route path="/create-user" element={<CreateUser />} />
                {/* Add more routes as needed */}
            </Routes>
        </BrowserRouter>
    )
}
export default RoutesApp;