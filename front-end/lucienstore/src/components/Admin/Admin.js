// Import necessary React and routing components
import React, { useState, useEffect } from 'react';

import axios from 'axios';
import './AdminPanel.css';
import UserTable from './UserRow';
import CategoryRow from './CategoryRow';
import ProductRow from './ProductRow';


const AppBar = () => {
    return (
        <div style={{ background: '#5a5656', padding: '10px', color: 'white', textAlign: 'center' }}>
            <h1>Admin Panel</h1>
        </div>
    );
};

const Admin = () => {
    const [users, setUsers] = useState([]);
    const [categories, setCategories] = useState([]);
    const [products, setProducts] = useState([]);

    // Define columns here
    const columns = React.useMemo(
        () => [
            { Header: 'User ID', accessor: 'id' },
            { Header: 'Name', accessor: 'name' },
            { Header: 'Email', accessor: 'email' },
            { Header: 'Phone Number', accessor: 'phoneNumber' },
            { Header: 'Accepted Terms', accessor: 'termAndConditionAccepted' },
            { Header: 'Role', accessor: 'role' },
            {
                Header: 'Authorities',
                accessor: 'authorities',
                Cell: ({ cell: { value } }) => value.map((authority, index) => (
                    <span key={index}>{authority.authority}{index !== value.length - 1 ? ', ' : ''}</span>
                )),
            },
        ],
        []
    );

    useEffect(() => {
        fetchData('categories', 'categories');
        fetchData('products', 'products');
        fetchData('users', 'users');
    }, []);

    const fetchData = async (endpoint, stateKey) => {
        try {
            const response = await axios.get(`http://localhost:8080/api/v1/${endpoint}`);
            if (stateKey === 'users') {
                setUsers(response.data);
            } else if (stateKey === 'categories') {
                setCategories(response.data);
            } else if (stateKey === 'products') {
                setProducts(response.data);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div>
            <AppBar />
            <div className="admin-panel">
                <div className="sidebar">
                    <ul>
                        <li>
                            <button onClick={() => fetchData('users', 'users')}>Users</button>
                        </li>
                        <li>
                            <button onClick={() => fetchData('products', 'products')}>Products</button>
                        </li>
                        <li>
                            <button onClick={() => fetchData('categories', 'categories')}>Categories</button>
                        </li>
                        <li>
                            <button onClick={() => fetchData('categories', 'categories')}>Orders</button>
                        </li>
                    </ul>
                </div>

                <div className="content">
                    {users.length > 0 && (
                        <div>
                            <h2>User Information</h2>
                            {/* Pass columns as a prop to UserTable */}
                            <UserTable columns={columns} data={users} />
                        </div>
                    )}

                    <h2></h2>
                    {categories.map(category => (
                        <CategoryRow key={category.id} category={category} />
                    ))}

                    <h2></h2>
                    {products.map(product => (
                        <ProductRow key={product.id} product={product} />
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Admin;