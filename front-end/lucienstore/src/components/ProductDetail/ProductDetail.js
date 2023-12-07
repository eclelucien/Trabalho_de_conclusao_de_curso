
import './ProductDetail.css';


import React, { useState } from "react";

import { AiOutlineHeart } from "react-icons/ai";
import { BiShoppingBag } from "react-icons/bi";
import ReactImageGallery from "react-image-gallery";
import Rater from "react-rater";
import "react-rater/lib/react-rater.css";

const ProductDetail = () => {
    const productDetailItem = {
        images: [
            {
                original:
                    "https://images.pexels.com/photos/90946/pexels-photo-90946.jpeg?auto=compress&cs=tinysrgb&w=600",
                thumbnail:
                    "https://images.pexels.com/photos/90946/pexels-photo-90946.jpeg?auto=compress&cs=tinysrgb&w=600",
            },
            {
                original:
                    "https://images.pexels.com/photos/1667088/pexels-photo-1667088.jpeg?auto=compress&cs=tinysrgb&w=600",
                thumbnail:
                    "https://images.pexels.com/photos/1667088/pexels-photo-1667088.jpeg?auto=compress&cs=tinysrgb&w=600",
            },
            {
                original:
                    "https://images.pexels.com/photos/2697787/pexels-photo-2697787.jpeg?auto=compress&cs=tinysrgb&w=600",
                thumbnail:
                    "https://images.pexels.com/photos/2697787/pexels-photo-2697787.jpeg?auto=compress&cs=tinysrgb&w=600",
            },
            {
                original:
                    "https://images.pexels.com/photos/3373736/pexels-photo-3373736.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                thumbnail:
                    "https://images.pexels.com/photos/3373736/pexels-photo-3373736.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            },
            {
                original:
                    "https://images.pexels.com/photos/3910071/pexels-photo-3910071.jpeg?auto=compress&cs=tinysrgb&w=600",
                thumbnail:
                    "https://images.pexels.com/photos/3910071/pexels-photo-3910071.jpeg?auto=compress&cs=tinysrgb&w=600",
            },
        ],
        title: "BIG ITALIAN SOFA",
        reviews: "150",
        availability: true,
        brand: "apex",
        category: "Sofa",
        sku: "BE45VGTRK",
        price: 450,
        previousPrice: 599,
        description:
            "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Quidem exercitationem voluptate sint eius ea assumenda provident eos repellendus qui neque! Velit ratione illo maiores voluptates commodi eaque illum, laudantium non!",
        size: ["XS", "S", "M", "L", "XL"],
        color: ["gray", "violet", "red"],
    };



    return (
        <section
            style={{
                display: 'grid',
                gridTemplateColumns: '1fr 1fr', // Two equal columns
                gap: '20px', // Adjust the gap based on your design
                margin: 'auto',
                maxWidth: '1200px',
                borderBottom: '1px solid #000',
                paddingBottom: '5px',
                paddingTop: '5px',
            }}
        >
            <div style={{ margin: 'auto', paddingLeft: '4px', paddingRight: '4px', height: '400px' }}>
                <ReactImageGallery
                    showBullets={false}
                    showFullscreenButton={false}
                    showPlayButton={false}
                    items={productDetailItem.images}

                />
            </div>

            <div className="product-info-container">
                <h2 className="product-title">{productDetailItem.title}</h2>
                <div className="rating-container">
                    <Rater
                        style={{ fontSize: "20px" }}
                        total={5}
                        interactive={false}
                        rating={productDetailItem.rating}
                    />
                    <p className="review-count">({productDetailItem.reviews})</p>
                </div>
                <p className="availability">
                    Availability:{" "}
                    {productDetailItem.availability ? (
                        <span className="in-stock">In Stock</span>
                    ) : (
                        <span className="expired">Expired</span>
                    )}
                </p>
                <div className="product-details-row">
                    <p className="product-detail">
                        Brand: <span className="font-normal">{productDetailItem.brand}</span>
                    </p>
                    <p className="product-detail">
                        Category: <span className="font-normal">{productDetailItem.category}</span>
                    </p>
                    <p className="product-detail">
                        SKU: <span className="font-normal">{productDetailItem.sku}</span>
                    </p>
                </div>
                <p className="price">
                    ${productDetailItem.price}{" "}
                    <span className="discount-price">
                        ${productDetailItem.previousPrice}
                    </span>
                </p>
                <p className="description">
                    {productDetailItem.description}
                </p>
                <div className="options-container">
                    <p className="options-title">Size</p>
                    <div className="options-list">
                        {productDetailItem.size.map((x, index) => (
                            <div
                                key={index}
                                className="color-option"
                            >
                                {x}
                            </div>
                        ))}
                    </div>
                </div>
                <div className="options-container">
                    <p className="options-title">Color</p>
                    <div className="options-list">
                        {productDetailItem.color.map((x, index) => (
                            <div
                                key={index}
                                className={`color-option bg-${x}-600`}
                            />
                        ))}
                    </div>
                </div>
                <div className="quantity-buttons-row">
                    <button className="quantity-btn">−</button>
                    <div className="quantity-display">1</div>
                    <button className="quantity-btn">+</button>
                </div>
                <div className="buttons-container">
                    <button className="button add-to-cart">
                        <BiShoppingBag className="" />
                        Add to cart
                    </button>
                    <button className="button wishlist">
                        <AiOutlineHeart className="" />
                        Wishlist
                    </button>
                </div>
            </div>

        </section>
    );
};

export default ProductDetail;