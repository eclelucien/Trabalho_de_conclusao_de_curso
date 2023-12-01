import React, { useState } from 'react';
import { useAuth } from '../../context/AuthContext';
import './CreateUser.css';

const CreateUser = () => {
    const { login } = useAuth();
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        password: '',
    });
    const [errorMessages, setErrorMessages] = useState({});
    const [isSubmitted, setIsSubmitted] = useState(false);

    const renderErrorMessage = (name) =>
        name === errorMessages.name && (
            <div className="error">{errorMessages.message}</div>
        );

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('https://lucienstore.azurewebsites.net/api/v1/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                const userData = await response.json();
                login(userData);
                setIsSubmitted(true);
            } else {
                const errorData = await response.json();
                setErrorMessages({ name: 'submit', message: errorData.message });
            }
        } catch (error) {
            console.error('Error submitting the form:', error);
        }
    };

    const renderForm = (
        <div className="form">
            <div className="title-message">Welcome to the Lucien Store family! 😀</div>

            <form onSubmit={handleSubmit}>
                <div className="input-container">
                    <label>Name </label>
                    <input type="text" name="uname" required />
                    {renderErrorMessage("uname")}
                </div>
                <div className="input-container">
                    <label>Email </label>
                    <input type="email" name="email" required />
                    {renderErrorMessage("email")}
                </div>
                <div className="input-container">
                    <label>Password </label>
                    <input type="password" name="pass" required />
                    {renderErrorMessage("pass")}
                </div>
                <div className="button-container">
                    <input
                        type="submit"
                        value="Submit"
                        style={{
                            backgroundColor: 'blue',
                            color: 'white',
                        }}
                    />
                </div>
            </form>


        </div>
    );

    return (
        <div className="loginApp">
            <div className="login-form">
                <div className="title">Sign Up</div>
                {isSubmitted ? <div>User is successfully logged in</div> : renderForm}
            </div>
        </div>
    );
};

export default CreateUser;
