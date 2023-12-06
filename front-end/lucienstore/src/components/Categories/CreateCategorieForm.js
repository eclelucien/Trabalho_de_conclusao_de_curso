import React, { useState } from 'react';

const CreateCategorieForm = ({ onSubmit, onCancel }) => {
    const [categorieData, setCategorieData] = useState({
        name: '',
        image: '',
        // Add other initial values as needed
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCategorieData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(categorieData);
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Name:
                <input
                    type="text"
                    name="name"
                    value={categorieData.name}
                    onChange={handleChange}
                />
            </label>
            <br />
            <label>
                Image:
                <input
                    type="text"
                    name="image"
                    value={categorieData.image}
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

export default CreateCategorieForm;
