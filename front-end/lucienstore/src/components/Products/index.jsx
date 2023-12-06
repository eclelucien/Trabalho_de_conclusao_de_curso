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
import EditProductForm from './EditProductForm';
import CreateProductForm from './CreateProductForm';

Modal.setAppElement('#root');


function Products() {
    const [search, setSearch] = useState('');
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(1);
    const [pagination, setPagination] = useState([]);
    const [selectedProductId, setSelectedProductId] = useState(null);
    const [editModalIsOpen, setEditModalIsOpen] = useState(false);
    const [createModalIsOpen, setCreateModalIsOpen] = useState(false);
    const [initialProductData, setInitialProductData] = useState(null);


    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/v1/products');
            setProducts(response.data);
            setPagination(calculateRange(response.data, 5));
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    const handleSearch = (event) => {
        setSearch(event.target.value);
        if (event.target.value !== '') {
            let searchResults = products.filter((product) =>
                product.name.toLowerCase().includes(search.toLowerCase()) ||
                product.description.toLowerCase().includes(search.toLowerCase())
            );
            setProducts(searchResults);
        } else {
            handleChangePage(1);
        }
    };

    const handleChangePage = (newPage) => {
        setPage(newPage);
    };


    const openEditModal = (productId) => {
        const selectedProduct = products.find(product => product.id === productId);

        setSelectedProductId(productId);
        setEditModalIsOpen(true);
        setInitialProductData(selectedProduct);
    };

    const closeEditModal = () => {
        setEditModalIsOpen(false);
        setSelectedProductId(null);
    };

    const openCreateModal = () => {
        setCreateModalIsOpen(true);
    };

    const closeCreateModal = () => {
        setCreateModalIsOpen(false);
    };

    const handleEditProduct = async (updatedProductData) => {
        try {
            await axios.put(`http://localhost:8080/api/v1/products/${selectedProductId}`, updatedProductData);
            fetchData();
            closeEditModal();

            toast.success('Product updated successfully');
        } catch (error) {
            console.error('Error updating product:', error);

            toast.error('Error updating product');
        }
    };

    const handleDeleteProduct = async (productId) => {
        try {
            await axios.delete(`http://localhost:8080/api/v1/products/${productId}`);
            fetchData();

            toast.success('Product deleted successfully');
        } catch (error) {
            console.error('Error deleting product:', error);

            toast.error('Error deleting product');
        }
    };

    const handleCreateProduct = async (newProductData) => {
        try {
            await axios.post('http://localhost:8080/api/v1/products', newProductData);
            fetchData();
            closeCreateModal();

            toast.success('Product created successfully');
        } catch (error) {
            console.error('Error creating product:', error);

            toast.error('Error creating product');
        }
    };




    return (
        <div className='dashboard-content'>
            <DashboardHeader btnText="New Product" onClick={() => openCreateModal()} />


            <div className='dashboard-content-container'>
                <div className='dashboard-content-header'>
                    <h2>Products List</h2>
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
                            <th>Description</th>
                            <th>Actions</th>
                        </tr>
                    </thead>

                    {products.length !== 0 ? (
                        <tbody>
                            {products.map((product, index) => (
                                <React.Fragment key={index}>
                                    <tr>
                                        <td>
                                            <span>{product.id}</span>
                                        </td>
                                        <td>
                                            <span>{product.name}</span>
                                        </td>
                                        <td>
                                            <span>{product.description}</span>
                                        </td>
                                        <td>
                                            <div>
                                                <img
                                                    src={EditIcon}
                                                    alt='edit-icon'
                                                    className='dashboard-content-icon'
                                                    onClick={() => openEditModal(product.id)}
                                                />
                                                <img
                                                    src={DeleteIcon}
                                                    alt='delete-icon'
                                                    className='dashboard-content-icon'
                                                    onClick={() => handleDeleteProduct(product.id)}
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
                {products.length !== 0 ? (
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
                    contentLabel='Edit Product Modal'
                    style={{
                        content: {
                            backgroundColor: '#DCDCDC',
                            maxWidth: '400px',
                            margin: 'auto',
                        },
                    }}
                >
                    <EditProductForm
                        onSubmit={handleEditProduct}
                        onCancel={closeEditModal}
                        initialData={initialProductData}

                    />
                </Modal>

                <Modal
                    isOpen={createModalIsOpen}
                    onRequestClose={closeCreateModal}
                    contentLabel='Create Product Modal'
                    style={{
                        content: {
                            backgroundColor: '#DCDCDC',
                            maxWidth: '400px',
                            margin: 'auto',
                        },
                    }}
                >
                    <CreateProductForm
                        onSubmit={handleCreateProduct}
                        onCancel={closeCreateModal}
                    />
                </Modal>
            </div>
        </div>
    );
}

export default Products;
