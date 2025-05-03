import axios from 'axios'
import { useRuntimeConfig } from '#app'

const config = useRuntimeConfig()


const apiClient = axios.create({
  baseURL: `http://${config.public.backendServer}:${config.public.backendPort}`,
  headers: {
    'Content-Type': 'application/json'
  }
})

// Interceptor para agregar el token JWT a cada solicitud
apiClient.interceptors.request.use((config) => {
  const token = localStorage.getItem('token')
  if (token) {
    config.headers.Authorization = `Bearer ${token}`
  }
  return config
})

export default apiClient