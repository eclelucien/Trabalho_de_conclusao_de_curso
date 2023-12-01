
import React from 'react';

const CategoryRow = ({ category }) => {
    return (
        <div className="category-row">
            <h3>Category ID: {category.id}</h3>
            <p>Name: {category.name}</p>
            <p>Description: {category.description || 'N/A'}</p>
        </div>
    );
};

export default CategoryRow;
