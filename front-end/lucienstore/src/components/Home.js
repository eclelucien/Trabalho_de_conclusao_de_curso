import React, { useState, useEffect } from 'react';
import WelcomeContainer from './WelcomeContainer';
import ImageCarousel from './Carousel/ImageCarousel';
import OptionsList from './OptionList/OptionsList';
import StoreAppBar from './StoreAppBar';
import Footer from './Footer/Footer';
import api from './../services/api';
import ProductListWrapper from './ProductList/ProductListWrapper';


const Home = ({ categories }) => {
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(1);
    const [hasMore, setHasMore] = useState(true);
    const [loading, setLoading] = useState(true);


    const loadProducts = async (pageNum) => {
        try {
            const response = await api.get("", {
                params: {
                    page: pageNum,
                },
            });

            console.log(response);

            const newProducts = response.data;

            if (Array.isArray(newProducts)) {
                setProducts((prevProducts) => [...prevProducts, ...newProducts]);

                if (newProducts.length === 0) {
                    setHasMore(false);
                }
            } else {
                console.error("API response does not contain an array of products:", response.data);
            }
        } catch (error) {
            console.error("Error loading products:", error);
        }

        setLoading(false);
    };


    useEffect(() => {
        loadProducts(1);
    }, []);

    const fetchMoreData = () => {
        setPage(page + 1);
        loadProducts(page + 1);
    };

    return (
        <div>
            <StoreAppBar />
            <WelcomeContainer categories={categories} />
            <ImageCarousel />
            <br />
            <div className="flex-container">
                <OptionsList />
                <ProductListWrapper
                    products={products}
                    fetchMoreData={fetchMoreData}
                    hasMore={hasMore}
                    loading={loading}
                />
            </div>
            <Footer />
        </div>
    );
};

export default Home;
