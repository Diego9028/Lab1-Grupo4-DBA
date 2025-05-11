<template>
    <div class="container">
        <form @submit.prevent="handleLogin">
            <h1 class="h3 mb-3 fw-normal">Iniciar Sesión</h1>
            <div class="form-floating">
                <input v-model="email" type="email" class="form-control" id="floatingInput"
                    placeholder="name@example.com" required>
                <label for="floatingInput">Correo Electrónico</label>
            </div>
            <div class="form-floating">
                <input v-model="password" type="password" class="form-control" id="floatingPassword"
                    placeholder="Password" required>
                <label for="floatingPassword">Contraseña</label>
            </div>
            <button class="btn btn-primary w-100 py-2" type="submit">Iniciar Sesión</button>
        </form>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useNuxtApp } from '#app'
import API_ROUTES from '../src/api-routes.js'
import { handleLogout } from '../src/services/authService.ts'

const email = ref('')
const password = ref('')
const router = useRouter()
const { $apiClient } = useNuxtApp()

const handleLogin = async () => {
    try {
        const response = await $apiClient.post(API_ROUTES.LOGIN, {
            email: email.value,
            password: password.value
        })

        const access_token = response.data.access_token // Obtener el token de la respuesta
        const refresh_token = response.data.refresh_token // Obtener el token de la respuesta

        if (!access_token || !refresh_token) {
            alert('Error: Token no recibido')
            return
        }
        localStorage.setItem('access_token', access_token)
        localStorage.setItem('refresh_token', refresh_token)
        router.push('/') // Redirigir a la página principal
    } catch (error) {
        console.error('Error:', error)
        alert(error.response?.data?.message || 'Error al iniciar sesión')
    }
}

const refreshToken = async () => {
    try {
        const refresh_token = localStorage.getItem('refresh_token')
        if (!refresh_token) {
            alert('No estás autenticado.')
            return
        }
        const response = await $apiClient.post(API_ROUTES.REFRESH_TOKEN, null, {
            headers: {
                Authorization: `Bearer ${refresh_token}`
            }
        })
        const new_access_token = response.data.access_token // Obtener el nuevo token de acceso de la respuesta
        if (!new_access_token) {
            alert('Error: Token no recibido')
            return
        }
        localStorage.setItem('access_token', new_access_token) // Actualizar el token en localStorage
    } catch (error) {
        console.error('Error al renoval el token:', error)
    }
}
</script>