import React from 'react';
import { Routes, Route } from 'react-router-dom';
import './Admin2.css';
import Orders from '../Orders';
import Categories from '../Categories';
import Users from '../Users';
import Products from '../Products';
import Layout from '../Layout/Layout';

function Admin2() {
  return (
    <Layout>
      <Routes>
        <Route path="/orders" element={<Orders />} />
        <Route path="/categories" element={<Categories />} />
        <Route path="/products" element={<Products />} />
        <Route path="/users" element={<Users />} />
        <Route path="/account" element={<div></div>} />
      </Routes>
    </Layout>
  );
}

export default Admin2;
