<!-- filepath: e:\Universidad\BBDDA\Lab_1\Lab1-Grupo4-DBA\Frontend\pages\index.vue -->
<template>
    <div>
      <h1>Bienvenido al Home</h1>
      <nuxt-link to="/login" class="btn btn-primary">Iniciar Sesión</nuxt-link>
      <nuxt-link to="/register" class="btn btn-secondary">Registrarse</nuxt-link>
      <nuxt-link to="/cliente" class="btn btn-info">Ver Clientes</nuxt-link>
      <nuxt-link to="/desempeno" class="btn btn-info">Ver Desempeño</nuxt-link>
      <nuxt-link to="/empresasTop" class="btn btn-info">Ver Empresas con Más Entregas</nuxt-link>
    </div>
  </template>

<script setup>
definePageMeta({
    middleware: 'auth'
  })
</script>