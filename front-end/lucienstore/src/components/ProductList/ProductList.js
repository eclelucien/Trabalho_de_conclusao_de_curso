import React, { useEffect, useState } from 'react';
import api from '../../services/api';
import InfiniteScroll from 'react-infinite-scroll-component';
import ProductCard from '../ProductCard/ProductCard';
import '../../App.css';

function ProductList() {
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(1);
    const [hasMore, setHasMore] = useState(true);
    const [loading, setLoading] = useState(true);

    const loadProducts = async (pageNum) => {
        try {
            const response = await api.get("", {
                params: {
                    // Add your API parameters here
                    page: pageNum,
                },
            });

            console.log(response);

            const newProducts = response.data;

            // Ensure newProducts is an array before spreading
            if (Array.isArray(newProducts)) {
                // Append the new products to the existing list
                setProducts((prevProducts) => [...prevProducts, ...newProducts]);

                if (newProducts.length === 0) {
                    // No more products available
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
                <h2>Loading products...</h2>
            </div>
        );
    }

    return (
        <InfiniteScroll
            dataLength={products.length}
            next={fetchMoreData}
            hasMore={hasMore}
            loader={<h4>Loading...</h4>}
        >
            <div className="product-list">
                {products.map((product) => (
                    <ProductCard key={product.id} product={product} />
                ))}
            </div>
        </InfiniteScroll>
    );
}

export default ProductList;
