import React, { useState } from 'react';

const CreateProductForm = ({ onSubmit, onCancel }) => {
    const [productData, setProductData] = useState({
        name: '',
        description: '',
        // Add other initial values as needed
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setProductData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(productData);
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Name:
                <input
                    type="text"
                    name="name"
                    value={productData.name}
                    onChange={handleChange}
                />
            </label>
            <br />
            <label>
                Email:
                <input
                    type="text"
                    name="email"
                    value={productData.description}
                    onChange={handleChange}
                />
            </label>
            <br />
            {/* Add other fields as needed */}
            <button type="submit">Create</button>
            <button type="button" onClick={onCancel}>
                Cancel
            </button>
        </form>
    );
};

export default CreateProductForm;
