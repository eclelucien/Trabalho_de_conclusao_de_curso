import React from 'react';

import '../App.css';


function WelcomeContainer({ categories }) {

    return (
        <div className="welcome-container">
            <h1>Welcome to Lucien Store</h1>
            <p>Explore our wide range of products and find great deals...</p>
            <ul className="categories-list">
                {categories.map((category, index) => (
                    <li key={index}>{category}</li>
                ))}
            </ul>
        </div>
    );
}

export default WelcomeContainer;
