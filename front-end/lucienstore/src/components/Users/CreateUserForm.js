import React, { useState } from 'react';

const CreateUserForm = ({ onSubmit, onCancel }) => {
    const [userData, setUserData] = useState({
        name: '',
        email: '',
        // Add other initial values as needed
    });

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
        <form onSubmit={handleSubmit}>
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
            {/* Add other fields as needed */}
            <button type="submit">Create</button>
            <button type="button" onClick={onCancel}>
                Cancel
            </button>
        </form>
    );
};

export default CreateUserForm;
