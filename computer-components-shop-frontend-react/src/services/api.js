import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
});

export const getClients = () => api.get('/clients');
export const getClientById = (id) => api.get(`/clients/${id}`);
export const createClient = (client) => api.post('/clients', client);
export const updateClient = (id, client) => api.put(`/clients/${id}`, client);
export const deleteClient = (id) => api.delete(`/clients/${id}`);

export const getProcessors = () => api.get('/processors');
export const getProcessorById = (id) => api.get(`/processors/${id}`);
export const createProcessor = (processor) => api.post('/processors', processor);
export const updateProcessor = (id, processor) => api.put(`/processors/${id}`, processor);
export const deleteProcessor = (id) => api.delete(`/processors/${id}`);

export const getGraphicCards = () => api.get('/graphic-cards');
export const getGraphicCardsById = (id) => api.get(`/graphic-cards/${id}`);
export const createGraphicCard = (graphicCard) => api.post('/graphic-cards', graphicCard);
export const updateGraphicCard = (id, graphicCard) => api.put(`/graphic-cards/${id}`, graphicCard);
export const deleteGraphicCard = (id) => api.delete(`/graphic-cards/${id}`);

export const getMotherboards = () => api.get('/motherboards');
export const getMotherboardsById = (id) => api.get(`/motherboards/${id}`);
export const createMotherboard = (motherboard) => api.post('/motherboards', motherboard);
export const updateMotherboard = (id, motherboard) => api.put(`/motherboards/${id}`, motherboard);
export const deleteMotherboard = (id) => api.delete(`/motherboards/${id}`);

export const getProducts = () => api.get('/products');
export const getProductsById = (id) => api.get(`/products/${id}`);
export const createProduct = (product) => api.post('/products', product);
export const updateProduct = (id, product) => api.put(`/products/${id}`, product);
export const deleteProduct = (id) => api.delete(`/products/${id}`);

export const getOrders = () => api.get('/orders');
export const getOrdersById = (id) => api.get(`/orders/${id}`);
export const createOrder = (order) => api.post('/orders', order);
export const updateOrder = (id, order) => api.put(`/orders/${id}`, order);
export const deleteOrder = (id) => api.delete(`/orders/${id}`);

export const getReviews = () => api.get('/reviews');
export const getReviewsById = (id) => api.get(`/reviews/${id}`);
export const createReview = (review) => api.post('/reviews', review);
export const updateReview = (id, review) => api.put(`/reviews/${id}`, review);
export const deleteReview = (id) => api.delete(`/reviews/${id}`);

export default api;