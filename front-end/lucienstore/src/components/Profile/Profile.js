
import React, { useState } from 'react';
import { Container, Typography, TextField, Button } from '@mui/material';
import { useAuth } from '../../context/AuthContext';
import './Profile.css';
import { toast, ToastContainer } from 'react-toastify';


function Profile() {
    const { user, updateUserProfile } = useAuth();
    const [userInfo, setUserInfo] = useState({
        name: user.name || '',
        email: user.email || '',
        phoneNumber: user.phoneNumber || '',
        role: user.role || '',
        address: user.address || ''
    });

    const [validationErrors, setValidationErrors] = useState({
        name: '',
        email: '',
        phoneNumber: '',
        role: '',
        address: ''
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setUserInfo((prevUserInfo) => ({
            ...prevUserInfo,
            [name]: value,
        }));

        setValidationErrors((prevErrors) => ({
            ...prevErrors,
            [name]: '',
        }));
    };

    const handleUpdateProfile = async (e) => {
        e.preventDefault();
        const validation = validateForm();
        if (validation.isValid) {
            try {
                await updateUserProfile(userInfo);
                toast.success('Profile updated successfully!', {
                    position: toast.POSITION.TOP_CENTER,
                });
                e.target.reset();
            } catch (error) {
                console.error('Error updating profile:', error.message);
                toast.error('Error updating profile. Please try again later.', {
                    position: toast.POSITION.TOP_CENTER,
                });
            }
        } else {
            setValidationErrors(validation.errors);
        }
    };


    const validateForm = () => {
        const errors = {
            name: '',
            email: '',
            phoneNumber: '',
            role: '',
            address: ''
        };

        let isValid = true;

        if (userInfo.name.trim() === '') {
            errors.email = 'Email is required';
            isValid = false;
        }
        if (userInfo.name.trim() === '') {
            errors.name = 'Name is required';
            isValid = false;
        }



        return { isValid, errors };
    };

    const renderErrorMessage = (fieldName) => {
        if (validationErrors[fieldName]) {
            return <div className="error-message">{validationErrors[fieldName]}</div>;
        }
        return null;
    };

    const handleSubmit = (e) => {
        e.preventDefault(); // Prevent default form submission
        handleUpdateProfile(); // Manually trigger the update profile logic
    };


    return (
        <div className="container">
            <Container maxWidth="sm" className="form">
                <Typography variant="h4" className="form-title" gutterBottom>
                    Profile
                </Typography>
                <form onSubmit={handleSubmit} noValidate>
                    <div className="input-container">
                        <label> Name </label>
                        <input
                            type="text"
                            name="name"
                            value={userInfo.name}
                            onChange={handleInputChange}
                            required
                        />
                        {renderErrorMessage("name")}
                    </div>

                    <div className="input-container">
                        <label>Email </label>
                        <input
                            type="text"
                            name="email"
                            value={userInfo.email}
                            onChange={handleInputChange}
                            required
                        />
                        {renderErrorMessage("email")}
                    </div>

                    <div className="input-container">
                        <label>Phone Number </label>
                        <input
                            type="text"
                            name="phoneNumber"
                            value={userInfo.phoneNumber}
                            onChange={handleInputChange}
                            required
                        />
                        {renderErrorMessage("phoneNumber")}
                    </div>

                    <div className="input-container">
                        <label>Role </label>
                        <input
                            type="text"
                            name="role"
                            value={userInfo.role}
                            onChange={handleInputChange}
                            required
                        />
                        {renderErrorMessage("role")}
                    </div>
                    <div className="input-container">
                        <label>Address </label>
                        <input
                            type="text"
                            name="address"
                            value={userInfo.address}
                            onChange={handleInputChange}
                            required
                        />
                        {renderErrorMessage("address")}
                    </div>

                    <Button
                        type="submit"
                        variant="contained"
                        color="primary"
                        className="form-button"
                        onClick={(e) => handleUpdateProfile(e)} // Pass the event parameter here
                    >
                        Update Profile
                    </Button>

                </form>
            </Container>
        </div>
    );
}

export default Profile;