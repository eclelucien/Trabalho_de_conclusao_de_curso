import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    const login = (userData) => {
        // Implement your login logic here
        setUser(userData);
        localStorage.setItem('user', JSON.stringify(userData));
    };

    const logout = () => {
        // Implement your logout logic here
        setUser(null);
        localStorage.removeItem('user');
    };

    useEffect(() => {
        // Check if user information is stored in localStorage on component mount
        const storedUser = localStorage.getItem('user');
        if (storedUser) {
            setUser(JSON.parse(storedUser));
        }
    }, []);

    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    return useContext(AuthContext);
};
