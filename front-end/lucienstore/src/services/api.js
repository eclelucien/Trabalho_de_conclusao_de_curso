import axios from 'axios';

// baseURL: 'https://lucienstore.azurewebsites.net/api/v1/'
// URL: https://lucienstore.azurewebsites.net/api/v1/

const api = axios.create({
    baseURL: 'https://fakestoreapi.com/products'
})

//https://lucienstore.azurewebsites.net/api/v1/


export default api;