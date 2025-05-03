<template>
  <div>
    <h1>Bienvenido al Home</h1>
    <router-link to="/login" class="btn btn-primary">Iniciar Sesión</router-link>
    <router-link to="/register" class="btn btn-secondary">Registrarse</router-link>
    <button @click="handleLogout" class="btn btn-danger">Cerrar Sesión</button>
  </div>
</template>

<script setup>
import { handleLogout } from './components/Login.vue'
definePageMeta({
  middleware: 'auth'
})
</script>


