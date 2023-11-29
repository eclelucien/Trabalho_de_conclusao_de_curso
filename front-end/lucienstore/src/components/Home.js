
import React from 'react';
import WelcomeContainer from './WelcomeContainer';
import ImageCarousel from './Carousel/ImageCarousel';
import OptionsList from './OptionList/OptionsList';
import ProductList from './ProductList/ProductList';
import StoreAppBar from './StoreAppBar';


const Home = ({ categories }) => {
    return (
        <div>
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
};

export default Home;
