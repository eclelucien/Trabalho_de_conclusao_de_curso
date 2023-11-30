import React from 'react';
import './ProductCard.css';
import { Link } from 'react-router-dom';

import '../../App.css';

const ProductCard = ({ product }) => {
  const maxLength = 100; // Set your desired maximum length for the description

  // Function to truncate long descriptions
  const truncateDescription = (text) => {
    return text.length > maxLength ? text.slice(0, maxLength) + '...' : text;
  };

  return (
    <div className="product-card">
      <Link to={`/product/${product.id}`}>
        <img src={product.image} alt={product.title} />
        <h3>{product.title}</h3>
      </Link>
      <p>ID: {product.id}</p>
      <p>Category: {product.category}</p>
      <p>Price: ${product.price}</p>
      <p>Description: {truncateDescription(product.description)}</p>
      <p>Rating: {product.rating.rate} ({product.rating.count} reviews)</p>
      <a href="#">Add to Cart</a>
    </div>
  );
};

export default ProductCard;
