

import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from '../components/Login/Login';
import Home from '../components/Home';
import CreateUser from '../components/CreateUser/CreateUser'
import { useAuth } from '../context/AuthContext';
import ProductDetail from '../components/ProductDetail/ProductDetail';
import Admin from '../components/Admin/Admin';
import Profile from '../components/Profile/Profile';



function RoutesApp() {
    const categories = ['Electronics', 'Clothing', 'Home Decor', 'Beauty', 'Books'];
    const { user } = useAuth();
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home categories={categories} />} />
                <Route path="/login" element={<Login />} />
                <Route path="/create-user" element={<CreateUser />} />
                <Route path="/product/:id" element={<ProductDetail />} />
                <Route path="/profile" element={<Profile />} />

                {/* Add the route for the Admin component */}
                <Route path="/admin" element={<Admin />} />


            </Routes>
        </BrowserRouter>
    )
}
export default RoutesApp;