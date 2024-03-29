import React, { useState } from 'react';
import './EditUserForm.css';

const EditUserForm = ({ onSubmit, onCancel, initialData }) => {
    const [userData, setUserData] = useState(initialData);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUserData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(userData);
    };

    return (
        <form className="edit-user-form" onSubmit={handleSubmit}>
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
                Email:
                <input
                    type="text"
                    name="email"
                    value={userData.email}
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

export default EditUserForm;
