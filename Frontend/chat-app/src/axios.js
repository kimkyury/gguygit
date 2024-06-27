import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8080/api/v1', // 기본 URL을 설정
});

export default instance;
