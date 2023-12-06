import React, { useState } from 'react';
import './EditCategorieForm.css';

const EditCategorieForm = ({ onSubmit, onCancel, initialData }) => {
    const [userData, setCategorieData] = useState(initialData);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCategorieData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(userData);
    };

    return (
        <form className="edit-categorie-form" onSubmit={handleSubmit}>
            <label>
                Name:
                <input
                    type="text"
                    name="name"
                    value={userData.name}
                    onChange={handleChange}
                />
            </label>
            <br />
            <label>
                Image:
                <input
                    type="text"
                    name="image"
                    value={userData.image}
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

export default EditCategorieForm;
