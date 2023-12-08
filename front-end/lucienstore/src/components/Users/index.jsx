import React, { useState, useEffect } from 'react';
import axios from 'axios';
import DashboardHeader from '../DashboardHeader';
import { calculateRange } from '../../utils/table-pagination';
import './styles.css';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Modal from 'react-modal';
import EditIcon from '../../assets/icons/icons8-edit.svg';
import DeleteIcon from '../../assets/icons/icons8-delete.svg';
import EditUserForm from './EditUserForm';
import CreateUserForm from './CreateUserForm';
import Layout from '../Layout/Layout';

Modal.setAppElement('#root');


function Users() {
    const [search, setSearch] = useState('');
    const [users, setUsers] = useState([]);
    const [page, setPage] = useState(1);
    const [pagination, setPagination] = useState([]);
    const [selectedUserId, setSelectedUserId] = useState(null);
    const [editModalIsOpen, setEditModalIsOpen] = useState(false);
    const [createModalIsOpen, setCreateModalIsOpen] = useState(false);
    const [initialUserData, setInitialUserData] = useState(null);


    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/users');
            setUsers(response.data);
            setPagination(calculateRange(response.data, 5));
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    const handleSearch = (event) => {
        setSearch(event.target.value);
        if (event.target.value !== '') {
            let searchResults = users.filter((user) =>
                user.name.toLowerCase().includes(search.toLowerCase()) ||
                user.email.toLowerCase().includes(search.toLowerCase())
            );
            setUsers(searchResults);
        } else {
            handleChangePage(1);
        }
    };

    const handleChangePage = (newPage) => {
        setPage(newPage);
    };


    const openEditModal = (userId) => {
        const selectedUser = users.find(user => user.id === userId);

        setSelectedUserId(userId);
        setEditModalIsOpen(true);
        setInitialUserData(selectedUser);
    };

    const closeEditModal = () => {
        setEditModalIsOpen(false);
        setSelectedUserId(null);
    };

    const openCreateModal = () => {
        setCreateModalIsOpen(true);
    };

    const closeCreateModal = () => {
        setCreateModalIsOpen(false);
    };

    const handleEditUser = async (updatedUserData) => {
        try {
            await axios.put(`http://localhost:8080/api/v1/users/${selectedUserId}`, updatedUserData);
            fetchData();
            closeEditModal();

            toast.success('User updated successfully');
        } catch (error) {
            console.error('Error updating user:', error);

            toast.error('Error updating user');
        }
    };

    const handleDeleteUser = async (userId) => {
        try {
            await axios.delete(`http://localhost:8080/api/v1/users/${userId}`);
            fetchData();

            toast.success('User deleted successfully');
        } catch (error) {
            console.error('Error deleting user:', error);

            toast.error('Error deleting user');
        }
    };

    const handleCreateUser = async (newUserData) => {
        try {
            await axios.post('http://localhost:8080/api/v1/users', newUserData);
            fetchData();
            closeCreateModal();

            toast.success('User created successfully');
        } catch (error) {
            console.error('Error creating user:', error);

            toast.error('Error creating user');
        }
    };




    return (
        <Layout>
            <div className='dashboard-content'>
                <DashboardHeader btnText="New User" onClick={() => openCreateModal()} />


                <div className='dashboard-content-container'>
                    <div className='dashboard-content-header'>
                        <h2>Users List</h2>
                        <div className='dashboard-content-search'>
                            <input
                                type='text'
                                value={search}
                                placeholder='Search..'
                                className='dashboard-content-input'
                                onChange={(e) => handleSearch(e)}
                            />
                        </div>
                    </div>

                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Actions</th>
                            </tr>
                        </thead>

                        {users.length !== 0 ? (
                            <tbody>
                                {users.map((user, index) => (
                                    <React.Fragment key={index}>
                                        <tr>
                                            <td>
                                                <span>{user.id}</span>
                                            </td>
                                            <td>
                                                <span>{user.name}</span>
                                            </td>
                                            <td>
                                                <span>{user.email}</span>
                                            </td>
                                            <td>
                                                <div>
                                                    <img
                                                        src={EditIcon}
                                                        alt='edit-icon'
                                                        className='dashboard-content-icon'
                                                        onClick={() => openEditModal(user.id)}
                                                    />
                                                    <img
                                                        src={DeleteIcon}
                                                        alt='delete-icon'
                                                        className='dashboard-content-icon'
                                                        onClick={() => handleDeleteUser(user.id)}
                                                    />
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colSpan="3">
                                            </td>
                                        </tr>
                                    </React.Fragment>
                                ))}
                            </tbody>
                        ) : null}
                    </table>
                    {users.length !== 0 ? (
                        <div className='dashboard-content-footer'>
                            {pagination.map((item, index) => (
                                <span
                                    key={index}
                                    className={item === page ? 'active-pagination' : 'pagination'}
                                    onClick={() => handleChangePage(item)}
                                >
                                    {item}
                                </span>
                            ))}
                        </div>
                    ) : (
                        <div className='dashboard-content-footer'>
                            <span className='empty-table'>No data</span>
                        </div>
                    )}
                    <Modal
                        isOpen={editModalIsOpen}
                        onRequestClose={closeEditModal}
                        contentLabel='Edit User Modal'
                        style={{
                            content: {
                                backgroundColor: '#DCDCDC',
                                maxWidth: '400px',
                                margin: 'auto',
                            },
                        }}
                    >
                        <EditUserForm
                            onSubmit={handleEditUser}
                            onCancel={closeEditModal}
                            initialData={initialUserData}

                        />
                    </Modal>

                    <Modal
                        isOpen={createModalIsOpen}
                        onRequestClose={closeCreateModal}
                        contentLabel='Create User Modal'
                        style={{
                            content: {
                                backgroundColor: '#DCDCDC',
                                maxWidth: '400px',
                                margin: 'auto',
                            },
                        }}
                    >
                        <CreateUserForm
                            onSubmit={handleCreateUser}
                            onCancel={closeCreateModal}
                        />
                    </Modal>
                </div>
            </div>
        </Layout>
    );
}

export default Users;
