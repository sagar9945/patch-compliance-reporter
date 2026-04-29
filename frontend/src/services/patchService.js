import api from './api'

const patchService = {
  getAll: (page = 0, size = 10) =>
    api.get('/patch-records', { params: { page, size } }),
  getById: (id) => api.get(`/patch-records/${id}`),
  create: (data) => api.post('/patch-records', data),
  update: (id, data) => api.put(`/patch-records/${id}`, data),
  remove: (id) => api.delete(`/patch-records/${id}`),
  search: (q) => api.get('/patch-records/search', { params: { q } }),
  getStats: () => api.get('/patch-records/stats'),
  getAiDescription: (id) =>
    api.post(`/patch-records/${id}/describe`),
  getAiRecommendation: (id) =>
    api.post(`/patch-records/${id}/recommend`),
}

export default patchService