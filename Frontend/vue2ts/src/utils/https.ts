// HTTP请求封装
import axios from 'axios';

// 创建axios实例
let service: any = {};
service = axios.create({
    baseURL: '/api',
    timeout: 50000,
});

// 配置request拦截器
service.interceptors.request.use(
    (config: any) => {
        return config;
    },
    (error: any) => {
        console.error('error:', error);
        Promise.reject(error);
    },
);
// 配置response拦截器
service.interceptors.response.use(
    (response: any) => {
        return response;
    },
    (error: any) => {
        console.error('error', error);
        Promise.reject(error);
    },
);

export default service;
