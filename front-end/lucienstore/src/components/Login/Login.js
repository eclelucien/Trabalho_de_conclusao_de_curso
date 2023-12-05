import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import axios from 'axios';

import './Login.css';

const Login = () => {
    const { login } = useAuth();
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        email: '',
        password: '',
    });

    const [errorMessages, setErrorMessages] = useState({});
    const [isSubmitted, setIsSubmitted] = useState(false);

    useEffect(() => {
        if (isSubmitted) {
            navigate('/');
        }
    }, [isSubmitted, navigate]);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const { email, password } = formData;

        try {
            const response = await axios.post('http://localhost:8080/api/v1/auth/login', {
                email,
                password,
            });

            const userData = response.data;
            login(userData);
            setIsSubmitted(true);
        } catch (error) {
            console.error('Error during login:', error);
            setErrorMessages({ name: "email", message: "Invalid email or password" });
        }
    };

    const renderErrorMessage = (name) =>
        name === errorMessages.name && (
            <div className="error">{errorMessages.message}</div>
        );

    const renderForm = (
        <div className="form">
            <form onSubmit={handleSubmit}>
                <div className="title-message">We missed you, welcome back! 😀</div>

                <div className="input-container">
                    <label>Email </label>
                    <input type="text" name="email" value={formData.email} onChange={handleChange} required />
                    {renderErrorMessage("email")}
                </div>
                <div className="input-container">
                    <label>Password </label>
                    <input type="password" name="password" value={formData.password} onChange={handleChange} required />
                    {renderErrorMessage("password")}
                </div>
                <div className="button-container">
                    <input
                        type="submit"
                        value="Login"
                        style={{
                            backgroundColor: 'blue',
                            color: 'white',
                        }}
                    />
                </div>
            </form>

            <div className="create-user-button">
                <Link to="/create-user" className="signup-link">Don't have an account yet? Sign Up</Link>
            </div>
        </div>
    );

    return (
        <div className="loginApp">
            <div className="login-form">
                <div className="title">Login</div>
                {isSubmitted ? <div>User is successfully logged in</div> : renderForm}
            </div>
        </div>
    );
};

export default Login;
