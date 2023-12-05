import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './CreateUser.css';

const Register = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: '',
        email: '',
        password: '',
        phoneNumber: '',
        termAndConditionAccepted: true,
        addressRequest: '',
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleCheckboxChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.checked,
        });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/api/v1/users', formData);

            if (response.status === 201) {
                toast.success('Registration successful!', {
                    position: toast.POSITION.TOP_CENTER,
                });

                navigate('/login');
            } else {
                toast.error('Registration failed. Please try again.', {
                    position: toast.POSITION.TOP_CENTER,
                });
            }
        } catch (error) {
            console.error('Error during registration:', error);

            toast.error('Registration failed. Please check your network connection and try again.', {
                position: toast.POSITION.TOP_CENTER,
            });
        }
    };


    return (
        <div className="registerApp">
            <div className="register-form">
                <div className="title">Register</div>
                <form onSubmit={handleSubmit}>
                    <div className="input-container">
                        <label>Name </label>
                        <input type="text" name="name" value={formData.name} onChange={handleChange} required />
                    </div>
                    <div className="input-container">
                        <label>Email </label>
                        <input type="email" name="email" value={formData.email} onChange={handleChange} required />
                    </div>
                    <div className="input-container">
                        <label>Password </label>
                        <input type="password" name="password" value={formData.password} onChange={handleChange} required />
                    </div>
                    <div className="input-container">
                        <label>Phone Number </label>
                        <input type="text" name="phoneNumber" value={formData.phoneNumber} onChange={handleChange} required />
                    </div>
                    <div className="input-container">
                        <label>Address </label>
                        <input type="text" name="addressRequest" value={formData.addressRequest} onChange={handleChange} required />
                    </div>
                    <div className="checkbox-container">
                        <label>
                            <input type="checkbox" name="termAndConditionAccepted" checked={formData.termAndConditionAccepted} onChange={handleCheckboxChange} required />
                            I accept the terms and conditions
                        </label>
                    </div>
                    <div className="button-container">
                        <input
                            type="submit"
                            value="Register"
                            style={{
                                backgroundColor: 'green',
                                color: 'white',
                            }}
                        />
                    </div>
                </form>

                <div className="login-link">
                    <Link to="/login">Already have an account? Login</Link>
                </div>
            </div>
        </div>
    );
};

export default Register;
