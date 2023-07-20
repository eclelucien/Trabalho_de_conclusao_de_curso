import React, { useState, useEffect } from 'react';
import StoreAppBar from './component/StoreAppBar';
import ProductCard from './component/ProductCard';
import OptionsList from './component/OptionsList'; // Import the OptionsList component
import './App.css';
import { Carousel } from 'react-responsive-carousel'; // Import the Carousel component
import 'react-responsive-carousel/lib/styles/carousel.min.css'; // Import the carousel styles

function App() {
  const [products, setProducts] = useState([]);
  const categories = ['Electronics', 'Clothing', 'Home Decor', 'Beauty', 'Books'];

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
      <div className="welcome-container">
        <h1>Welcome to Lucien Store</h1>
        <p>
          Explore our wide range of products and find great deals...
        </p>
        <ul className="categories-list">
          {categories.map((category, index) => (
            <li key={index}>{category}</li>
          ))}
        </ul>
      </div>
      <Carousel showArrows={true} showThumbs={false} interval={3000}>
          <div>
            <img src="https://img.ltwebstatic.com/images3_ccc/2023/07/17/16895783130a5b4a7ee74d58ae920e7a8a8f80c6d7_thumbnail_2000x.webp" alt="ImageSlide 2" />
          </div>
          <div>
            <img src="https://img.ltwebstatic.com/images3_ccc/2023/07/10/1688960268466f26d41b9e612de630272752d840d2_thumbnail_2000x.webp" alt="ImageSlide 1" />
          </div>
          <div>
            <img src="https://img.ltwebstatic.com/images3_ccc/2023/07/17/16895783130a5b4a7ee74d58ae920e7a8a8f80c6d7_thumbnail_2000x.webp" alt="ImageSlide 2" />
          </div>
        </Carousel>
        <br/>
      <div className="flex-container">
        <OptionsList />
        <div className="product-list">
          {products.map((product) => (
            <ProductCard key={product.id} product={product} />
          ))}
        </div>
      </div>
    </div>
  );
}

export default App;
