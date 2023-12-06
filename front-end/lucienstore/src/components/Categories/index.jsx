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
import EditCategorieForm from './EditCategorieForm';
import CreateCategorieForm from './CreateCategorieForm';

Modal.setAppElement('#root');


function Categories() {
    const [search, setSearch] = useState('');
    const [categories, setCategories] = useState([]);
    const [page, setPage] = useState(1);
    const [pagination, setPagination] = useState([]);
    const [selectedCategorieId, setSelectedCategorieId] = useState(null);
    const [editModalIsOpen, setEditModalIsOpen] = useState(false);
    const [createModalIsOpen, setCreateModalIsOpen] = useState(false);
    const [initialCategorieData, setInitialCategorieData] = useState(null);


    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/categories');
            setCategories(response.data);
            setPagination(calculateRange(response.data, 5));
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    const handleSearch = (event) => {
        setSearch(event.target.value);
        if (event.target.value !== '') {
            let searchResults = categories.filter((categorie) =>
                categorie.name.toLowerCase().includes(search.toLowerCase()) ||
                categorie.email.toLowerCase().includes(search.toLowerCase())
            );
            setCategories(searchResults);
        } else {
            handleChangePage(1);
        }
    };

    const handleChangePage = (newPage) => {
        setPage(newPage);
    };


    const openEditModal = (categorieId) => {
        const selectedCategorie = categories.find(categorie => categorie.id === categorieId);

        setSelectedCategorieId(categorieId);
        setEditModalIsOpen(true);
        setInitialCategorieData(selectedCategorie);
    };

    const closeEditModal = () => {
        setEditModalIsOpen(false);
        setSelectedCategorieId(null);
    };

    const openCreateModal = () => {
        setCreateModalIsOpen(true);
    };

    const closeCreateModal = () => {
        setCreateModalIsOpen(false);
    };

    const handleEditCategorie = async (updatedCategorieData) => {
        try {
            await axios.put(`http://localhost:8080/api/v1/categories/${selectedCategorieId}`, updatedCategorieData);
            fetchData();
            closeEditModal();

            toast.success('Categorie updated successfully');
        } catch (error) {
            console.error('Error updating categorie:', error);

            toast.error('Error updating categorie');
        }
    };

    const handleDeleteCategorie = async (categorieId) => {
        try {
            await axios.delete(`http://localhost:8080/api/v1/categories/${categorieId}`);
            fetchData();

            toast.success('Categorie deleted successfully');
        } catch (error) {
            console.error('Error deleting categorie:', error);

            toast.error('Error deleting categorie');
        }
    };

    const handleCreateCategorie = async (newCategorieData) => {
        try {
            await axios.post('http://localhost:8080/api/v1/categories', newCategorieData);
            fetchData();
            closeCreateModal();

            toast.success('Categorie created successfully');
        } catch (error) {
            console.error('Error creating categorie:', error);

            toast.error('Error creating categorie');
        }
    };




    return (
        <div className='dashboard-content'>
            <DashboardHeader btnText="New Categorie" onClick={() => openCreateModal()} />


            <div className='dashboard-content-container'>
                <div className='dashboard-content-header'>
                    <h2>Categories List</h2>
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
                            <th>Image</th>
                            <th>Actions</th>
                        </tr>
                    </thead>

                    {categories.length !== 0 ? (
                        <tbody>
                            {categories.map((categorie, index) => (
                                <React.Fragment key={index}>
                                    <tr>
                                        <td>
                                            <span>{categorie.id}</span>
                                        </td>
                                        <td>
                                            <span>{categorie.name}</span>
                                        </td>
                                        <td>
                                            <span>{categorie.image}</span>
                                        </td>
                                        <td>
                                            <div>
                                                <img
                                                    src={EditIcon}
                                                    alt='edit-icon'
                                                    className='dashboard-content-icon'
                                                    onClick={() => openEditModal(categorie.id)}
                                                />
                                                <img
                                                    src={DeleteIcon}
                                                    alt='delete-icon'
                                                    className='dashboard-content-icon'
                                                    onClick={() => handleDeleteCategorie(categorie.id)}
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
                {categories.length !== 0 ? (
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
                    contentLabel='Edit Categorie Modal'
                    style={{
                        content: {
                            backgroundColor: '#DCDCDC',
                            maxWidth: '400px',
                            margin: 'auto',
                        },
                    }}
                >
                    <EditCategorieForm
                        onSubmit={handleEditCategorie}
                        onCancel={closeEditModal}
                        initialData={initialCategorieData}

                    />
                </Modal>

                <Modal
                    isOpen={createModalIsOpen}
                    onRequestClose={closeCreateModal}
                    contentLabel='Create Categorie Modal'
                    style={{
                        content: {
                            backgroundColor: '#DCDCDC',
                            maxWidth: '400px',
                            margin: 'auto',
                        },
                    }}
                >
                    <CreateCategorieForm
                        onSubmit={handleCreateCategorie}
                        onCancel={closeCreateModal}
                    />
                </Modal>
            </div>
        </div>
    );
}

export default Categories;
