import React, { useEffect, useState } from 'react';
import api from '../../services/api';
import InfiniteScroll from 'react-infinite-scroll-component';
import ProductCard from '../ProductCard/ProductCard';
import '../../App.css';

function ProductList({ initialProducts }) {
    const [products, setProducts] = useState(initialProducts || []);
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

    if (loading) {
        return (
            <div className='loading'>
                <h2 style={{ color: 'blue', textAlign: 'center' }}>Loading products...</h2>
            </div>
        );
    }

    return (
        <div className="product-list">
            {products.map((product) => (
                <ProductCard key={product.id} product={product} />
            ))}
        </div>
    );
}

export default ProductList;
