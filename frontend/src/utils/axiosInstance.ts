import axios from 'axios';
import { API_BASE_URL } from '@/config';

export const axiosInstance = axios.create({

    baseURL: API_BASE_URL,
    timeout: 5000,
});

axiosInstance.interceptors.response.use(
    response => response,
    error => {
        console.error('API Error:', error);
        return Promise.reject(error);
    }
);
