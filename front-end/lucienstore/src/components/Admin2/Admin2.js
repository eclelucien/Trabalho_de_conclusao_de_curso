import React from 'react';
import { Routes, Route } from 'react-router-dom';

import SideBar from '../Sidebar';
import sidebar_menu from '../constants/sidebar-menu';

import './Admin2.css';
import Orders from '../Orders';
import Categories from '../Categories';
import Users from '../Users';
import Products from '../Products';

function Admin2() {
  return (
    <div className='dashboard-container'>
      <SideBar menu={sidebar_menu} />

      <div className='dashboard-body'>
        <Routes>
          <Route path="/orders" element={<Orders />} />
          <Route path="/categories" element={<Categories />} />
          <Route path="/products" element={<Products />} />
          <Route path="/users" element={<Users />} />
          <Route path="/account" element={<div></div>} />
        </Routes>
      </div>
    </div>
  );
}

export default Admin2;
