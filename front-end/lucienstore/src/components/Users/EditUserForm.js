import React, { useState } from 'react';

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
            <button type="submit">Save</button>
            <button type="button" onClick={onCancel}>
                Cancel
            </button>
        </form>
    );
};

export default EditUserForm;
