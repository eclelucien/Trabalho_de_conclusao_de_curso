import React, { useState } from 'react';
import './EditProductForm.css';

const EditProductForm = ({ onSubmit, onCancel, initialData }) => {
    const [productData, setProductData] = useState(initialData);

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
        <form className="edit-product-form" onSubmit={handleSubmit}>
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
                    name="description"
                    value={productData.description}
                    onChange={handleChange}
                />
            </label>
            <br />
            <div className="button-container">
                <button type="submit" className="save-button">
                    Save
                </button>
                <button type="button" onClick={onCancel} className="cancel-button">
                    Cancel
                </button>
            </div>
        </form>
    );
};

export default EditProductForm;
