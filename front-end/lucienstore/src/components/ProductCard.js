import React from 'react';
import './ProductCard.css';
import '../App.css';


const ProductCard = ({ product }) => {
  return (
    <div className="product-card">
      <img src={product.thumbnailUrl} alt={product.title} />
      <h3>{product.title}</h3>
      <p>Album ID: {product.albumId}</p>
      <p>ID: {product.id}</p>
      <a href={product.url}>View Product</a>
    </div>
  );
};


export default ProductCard;
