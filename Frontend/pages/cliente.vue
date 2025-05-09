<template>
    <div class="container">
        <h1 class="title">Lista de Clientes</h1>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Dirección</th>
                    <th>Correo</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="cliente in clientes" :key="cliente.id">
                    <td>{{ cliente.id }}</td>
                    <td>{{ cliente.nombre }}</td>
                    <td>{{ cliente.direccion }}</td>
                    <td>{{ cliente.correo }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useNuxtApp } from '#app'
import API_ROUTES from '../src/api-routes.js'

const clientes = ref([])
const { $apiClient } = useNuxtApp()

const fetchClientes = async () => {
    try {
        const response = await $apiClient.get(API_ROUTES.CLIENTE + '/')
        clientes.value = response.data
    } catch (error) {
        console.error('Error al obtener clientes:', error)
        console.log(error.response?.data?.message || 'Error al obtener clientes')
        alert('No se pudieron cargar los clientes.')
    }
}

onMounted(() => {
    fetchClientes()
})
</script>