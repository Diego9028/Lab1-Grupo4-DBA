<template>
    <div class="container mt-5">
        <form @submit.prevent="handleRegister" class="form-register">
            <h1 class="h3 mb-3 fw-normal text-center">Registro</h1>
            <div class="form-floating mb-3">
                <input v-model="name" type="text" class="form-control" id="floatingName" placeholder="Name" required />
                <label for="floatingName">Nombre</label>
            </div>
            <div class="form-floating mb-3">
                <input v-model="direccion" type="text" class="form-control" id="floatingDireccion" placeholder="Dirección" required= />
                <label for="floatingDireccion">Dirección</label>
            </div>
            <div class="form-floating mb-3">
                <input v-model="email" type="email" class="form-control" id="floatingEmail" placeholder="Correo Electrónico" required />
                <label for="floatingEmail">Correo Electrónico</label>
            </div>
            <div class="form-floating mb-3">
                <input v-model="password" type="password" class="form-control" id="floatingPassword" placeholder="Contraseña" required />
                <label for="floatingPassword">Contraseña</label>
            </div>
            <button class="btn btn-primary w-100 py-2" type="submit">Registrar</button>
        </form>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useNuxtApp } from '#app'
import API_ROUTES from '../src/api-routes'

const name = ref('')
const direccion = ref('')
const email = ref('')
const password = ref('')
const router = useRouter()
const { $apiClient } = useNuxtApp()

const handleRegister = async () => {
    try {
        const response = await $apiClient.post(API_ROUTES.REGISTER, {
            name: name.value,
            direccion: direccion.value,
            email: email.value,
            password: password.value,
        })
        alert('Registro exitoso, ahora puedes iniciar sesión')
        router.push('/login') 
    } catch (error) {
        console.error('Error al registrar:', error)
        alert(error.response?.data?.message || 'Error al registrar')
    }
}
</script>

<style scoped>
.container {
    max-width: 400px;
    margin: auto;
}
</style>