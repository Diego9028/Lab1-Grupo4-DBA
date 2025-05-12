import API_ROUTES from '../api-routes'
import type { AxiosInstance } from 'axios'
import type { Router } from 'vue-router'

export const handleLogout = async (apiClient: AxiosInstance, router: Router) => {
  try {
    if (typeof window === 'undefined') {
      console.warn('localStorage no está disponible en el servidor.')
      return
    }
    const access_token = localStorage.getItem('access_token')

    if (!access_token) {
      alert('No estás autenticado.')
      return
    }

    await apiClient.post(API_ROUTES.LOGOUT, null, {
      headers: {
        Authorization: `Bearer ${access_token}`
      }
    })


    localStorage.removeItem('access_token')
    localStorage.removeItem('refresh_token')
    alert('Sesión cerrada exitosamente.')
    router.push('/login') 
  } catch (error) {
    console.error('Error al cerrar sesión:', error)
    alert("Error al cerrar sesión: ")
    return false;
  }
}