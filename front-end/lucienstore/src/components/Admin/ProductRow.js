
import React from 'react';

const ProductRow = ({ product }) => {
    return (
        <div className="product-row">
            <h3>Product ID: {product.id}</h3>
            <p>Name: {product.name}</p>
            <p>Description: {product.description || 'N/A'}</p>
            <p>Price: {product.price}</p>
        </div>
    );
};

export default ProductRow;
