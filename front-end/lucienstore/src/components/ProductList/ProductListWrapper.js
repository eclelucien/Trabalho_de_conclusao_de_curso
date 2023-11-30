import React from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';
import ProductList from './ProductList';

const ProductListWrapper = ({ products, fetchMoreData, hasMore, loading }) => {
    return (
        <InfiniteScroll
            dataLength={products.length}
            next={fetchMoreData}
            hasMore={hasMore}
            loader={<h2 style={{ color: 'blue', textAlign: 'center', fontSize: '15px' }}>Loading more products...</h2>}
        >
            <ProductList products={products} loading={loading} />
        </InfiniteScroll>
    );
};

export default ProductListWrapper;
