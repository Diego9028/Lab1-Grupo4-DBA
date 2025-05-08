import API_ROUTES from '../api-routes'
import type { AxiosInstance } from 'axios'
import type { Router } from 'vue-router'

export const handleLogout = async (apiClient: AxiosInstance, router: Router) => {
    try {
        if(typeof window === 'undefined') {
            console.warn('localStorage no está disponible en el servidor.')
            return
        }
        const token = localStorage.getItem('token')
    if (!token) {
      alert('No estás autenticado.')
      return
    }

    await apiClient.post(API_ROUTES.LOGOUT, null, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })

    // Limpiar el token del almacenamiento local
    localStorage.removeItem('token')
    alert('Sesión cerrada exitosamente.')
    router.push('/login') // Redirigir al login
  } catch (error) {
    console.error('Error al cerrar sesión:', error)
    alert("Error al cerrar sesión: ")
    return false;
  }
}