
import React from 'react';
import './ProductDetail.css';
import { useParams } from 'react-router-dom';
import StoreAppBar from '../StoreAppBar';


const ProductDetail = () => {
    const { id } = useParams();
    const product = {};

    return (

        <div className="product-detail">
            <StoreAppBar />

            <img src="https://fastly.picsum.photos/id/8/5000/3333.jpg?hmac=OeG5ufhPYQBd6Rx1TAldAuF92lhCzAhKQKttGfawWuA" alt={product.title} />
            <div className="details">
                <h2>Product title</h2>
                <p>ID: 255</p>
                <p>Category: Men</p>
                <p>Price: $2564.45</p>
                <p>Description: Test desc</p>
                <p>Rating: 2 33 reviews</p>
            </div>
        </div>
    );
};

export default ProductDetail;
