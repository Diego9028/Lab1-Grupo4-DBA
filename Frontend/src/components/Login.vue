<template>
    <form @submit.prevent="handleLogin">
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
        <div class="form-floating">
            <input v-model="email" type="email" class="form-control" id="floatingInput" placeholder="name@example.com"
                required>
            <label for="floatingInput">Email address</label>
        </div>
        <div class="form-floating">
            <input v-model="password" type="password" class="form-control" id="floatingPassword" placeholder="Password"
                required>
            <label for="floatingPassword">Password</label>
        </div>
        <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
    </form>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import API_ROUTES from '../apiRoutes.js'
import apiClient from '../http-common.js'

const email = ref('')
const password = ref('')
const router = useRouter()

const handleLogin = async () => {
    try {
        const response = await apiClient.post(API_ROUTES.LOGIN, {
            email: email.value,
            password: password.value
        })
        localStorage.setItem('token', response.data.token) // Guardar el token en localStorage
        router.push('/') // Redirigir a la página principal
    } catch (error) {
        console.error('Error:', error)
        alert(error.response?.data?.message || 'Error al iniciar sesión')
    }
}

const handleLogout = async () => {
  try {
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
    alert('Error al cerrar sesión.')
  }
}

const refreshToken = async () => {
    try {
        const token = localStorage.getItem('token')
        const response = await apiClient.post(API_ROUTES.REFRESH_TOKEN, null {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        localStorage.setItem('token', response.data.token) // Actualizar el token en localStorage
    } catch (error) {
        console.error('Error al renoval el token:', error)
    }
}
</script>