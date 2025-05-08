import { defineNuxtPlugin } from '#app'
import axios from 'axios'

export default defineNuxtPlugin((nuxtApp) => {
  const config = useRuntimeConfig()

  // Crear una instancia de Axios
  const apiClient = axios.create({
    baseURL: `http://${config.public.backendServer}:${config.public.backendPort}`,
    headers: {
      'Content-Type': 'application/json',
    },
  })

  // Interceptor para agregar el token JWT a cada solicitud
  apiClient.interceptors.request.use((config) => {
    const token = localStorage.getItem('token')
    const publicRoutes = ['/auth/login', 'auth/register', '/auth/refresh-token', '/auth/logout']
    if (token && !publicRoutes.includes(config.url || '')) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  })

  // Hacer que la instancia esté disponible globalmente
  nuxtApp.provide('apiClient', apiClient)
})