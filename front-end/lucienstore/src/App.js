
import React from 'react';
import StoreAppBar from './components/StoreAppBar';
import WelcomeContainer from './components/WelcomeContainer';
import ImageCarousel from './components/ImageCarousel';
import OptionsList from './components/OptionsList';
import ProductList from './components/ProductList';
import { useAuth } from './context/AuthContext';
import './App.css';

function App() {
  const categories = ['Electronics', 'Clothing', 'Home Decor', 'Beauty', 'Books'];
  const { user } = useAuth();

  return (
    <div className="App">
      <StoreAppBar />
      <WelcomeContainer categories={categories} />
      <ImageCarousel />
      <br />
      <div className="flex-container">
        <OptionsList />
        <ProductList />
      </div>
    </div>
  );
}

export default App;
