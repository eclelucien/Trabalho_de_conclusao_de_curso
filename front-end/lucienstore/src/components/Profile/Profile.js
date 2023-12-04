// Profile.js

import React, { useState } from 'react';
import { Container, Typography, TextField, Button } from '@mui/material';
import { useAuth } from '../../context/AuthContext';

function Profile() {
    const { user, updateUserProfile } = useAuth();
    const [userInfo, setUserInfo] = useState({
        name: user.name || '',
        email: user.email || '',
        phoneNumber: user.phoneNumber || '',
        role: user.role || '',
        address: user.address || ''
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setUserInfo((prevUserInfo) => ({
            ...prevUserInfo,
            [name]: value,
        }));
    };

    const handleUpdateProfile = async () => {
        try {
            await updateUserProfile(userInfo);
            console.log('Profile updated successfully!');
        } catch (error) {
            console.error('Error updating profile:', error.message);
        }
    };

    return (
        <div style={{ backgroundColor: '#f0f0f0', minHeight: '100vh' }}>
            <Container maxWidth="sm">
                <Typography variant="h4" gutterBottom>
                    Profile
                </Typography>
                <form>
                    <TextField
                        fullWidth
                        margin="normal"
                        label="Display Name"
                        name="name"
                        value={userInfo.name}
                        onChange={handleInputChange}
                    />
                    <TextField
                        fullWidth
                        margin="normal"
                        label="Email"
                        name="email"
                        value={userInfo.email}
                        onChange={handleInputChange}
                    />
                    <TextField
                        fullWidth
                        margin="normal"
                        label="Phone Number"
                        name="phoneNumber"
                        value={userInfo.phoneNumber}
                        onChange={handleInputChange}
                    />
                    <TextField
                        fullWidth
                        margin="normal"
                        label="Role"
                        name="role"
                        value={userInfo.role}
                        onChange={handleInputChange}
                    />
                    <TextField
                        fullWidth
                        margin="normal"
                        label="Address"
                        name="address"
                        value={userInfo.address}
                        onChange={handleInputChange}
                    />
                    {/* Add other input fields for additional user information */}
                    <Button variant="contained" color="primary" onClick={handleUpdateProfile}>
                        Update Profile
                    </Button>
                </form>
            </Container>
        </div>
    );
}

export default Profile;
