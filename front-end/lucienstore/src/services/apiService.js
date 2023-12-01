// apiService.js
import api from '../services/api';

// Fetch users from the API using the configured Axios instance
export const getUsers = async () => {
    console.log("Calling getUsers...");
    try {
        const response = await api.get('/users');
        console.log("getUsers Response:", response.data);
        return response.data;
    } catch (error) {
        console.error('Error fetching users:', error.message);
        throw Error(`Error fetching users: ${error.message}`);
    }
};

// Create a new user
export const createUser = async (userData) => {
    try {
        const response = await api.post('/users', userData);
        return response.data;
    } catch (error) {
        throw Error(`Error creating user: ${error.message}`);
    }
};

// Update an existing user
export const updateUser = async (userId, userData) => {
    try {
        const response = await api.put(`/users/${userId}`, userData);
        return response.data;
    } catch (error) {
        throw Error(`Error updating user: ${error.message}`);
    }
};

// Delete an existing user
export const deleteUser = async (userId) => {
    try {
        const response = await api.delete(`/users/${userId}`);
        return response.data;
    } catch (error) {
        throw Error(`Error deleting user: ${error.message}`);
    }
};
