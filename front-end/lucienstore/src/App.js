import React, { useState, useEffect } from 'react';
import StoreAppBar from './StoreAppBar';
import ProductCard from './component/ProductCard';

function App() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await fetch('https://jsonplaceholder.typicode.com/photos');
      const data = await response.json();
      setProducts(data);
    } catch (error) {
      console.log('Error fetching products:', error);
    }
  };

  return (
    <div className="App">
      <StoreAppBar />
      <h1>Welcome to Lucien Store!</h1>
      <p>Explore our wide range of products and find great deals.</p>
      <div className="product-list">
        {products.map((product) => (
          <ProductCard key={product.id} product={product} />
        ))}
      </div>
    </div>
  );
}

export default App;
