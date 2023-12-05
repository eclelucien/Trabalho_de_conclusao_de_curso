

import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from '../components/Login/Login';
import Home from '../components/Home';
import CreateUser from '../components/CreateUser/CreateUser'
import { useAuth } from '../context/AuthContext';
import ProductDetail from '../components/ProductDetail/ProductDetail';
import Admin from '../components/Admin/Admin';
import Profile from '../components/Profile/Profile';
import Admin2 from '../components/Admin2/Admin2';
import Orders from '../components/Orders';
import Categories from '../components/Categories';
import Users from '../components/Users';
import Products from '../components/Products';



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

                {/*  Admin routes */}
                <Route path="/admin" element={<Admin />} />
                <Route path="/admin2" element={<Admin2 />} />
                <Route path="/orders" element={< Orders />} />
                <Route path="/categories" element={<Categories />} />
                <Route path="/products" element={<Products />} />
                <Route path="/users" element={<Users />} />



            </Routes>
        </BrowserRouter>
    )
}
export default RoutesApp;