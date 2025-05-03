<template>
    <form @submit.prevent="handleRegister">
        <h1 class="h3 mb-3 fw-normal">Register</h1>
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
        <button class="btn btn-primary w-100 py-2" type="submit">Register</button>
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

const handleRegister = async () => {
    try {
        const response = await apiClient.post(API_ROUTES.REGISTER, {
            email: email.value,
            password: password.value
        })
        alert('Registro exitoso, ahora puedes iniciar sesión')
        router.push('/login') // Redirigir al login
    } catch (error) {
        console.error('Error:', error)
        alert(error.response?.data?.message || 'Error al registrar')
    }
}
</script>