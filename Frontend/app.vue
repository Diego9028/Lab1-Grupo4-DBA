<template>
  <div>
    <header>
      <h1>Mi Aplicación</h1>
      <nav>
        <nuxt-link to="/" class="btn btn-link">Inicio</nuxt-link>
        <nuxt-link to="/login" class="btn btn-link">Iniciar Sesión</nuxt-link>
        <nuxt-link to="/register" class="btn btn-link">Registrarse</nuxt-link>
        <nuxt-link to="/cliente" class="btn btn-link">Clientes</nuxt-link>
        <nuxt-link to="/desempeno" class="btn btn-link">Desempeño</nuxt-link>
      </nav>
    </header>
    <main>
      <nuxt-page />
    </main>
    <footer>
      <p>© 2025 Mi Aplicación</p>
    </footer>
  </div>
</template>


<script setup>
import { handleLogout } from './src/services/authService'
import { useNuxtApp } from '#app'
import { useRouter } from 'vue-router'

const { $apiClient } = useNuxtApp()
const router = useRouter()

const logoutUser = async () => {
  await handleLogout($apiClient, router)
}

</script>