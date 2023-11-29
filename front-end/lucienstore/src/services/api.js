import axios from 'axios';

// baseURL: 'https://api.themoviedb.org/3/'
// URL: /movie/now_playing?api_key=your_api_key&language=pt-BR

const api = axios.create({
    baseURL: 'https://jsonplaceholder.typicode.com/photos'
})

//https://lucienstore.azurewebsites.net/api/v1/


export default api;