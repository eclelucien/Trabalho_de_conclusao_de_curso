// App.js

import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import StoreAppBar from './components/StoreAppBar';
import Home from './components/Home';
import Login from './components/Login/Login';
import { useAuth } from './context/AuthContext';

import './App.css';

function App() {
  const categories = ['Electronics', 'Clothing', 'Home Decor', 'Beauty', 'Books'];
  const { user } = useAuth();

  return (
    <Router>
      <div className="App">
        <StoreAppBar />
        <Routes>
          <Route
            path="/"
            element={user ? <Home categories={categories} /> : <Navigate to="/login" />}
          />
          <Route path="/login" element={user ? <Navigate to="/" /> : <Login />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
