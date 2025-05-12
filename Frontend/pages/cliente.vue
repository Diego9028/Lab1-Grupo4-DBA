<template>
    <div class="container">
        <h1 class="title">Lista de Clientes</h1>
        <div class="buttons">
            <button @click="fetchClientes('/')">Cargar Clientes 2</button>
        </div>
        <div class="search">
            <input
                type="text"
                v-model="correo"
                placeholder="Buscar cliente por correo"
                class="input"
            />
            <button @click="buscarClientePorCorreo">Buscar</button>
        </div>
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
                <tr v-for="cliente in clientes" :key="cliente.id_cliente">
                    <td>{{ cliente.id_cliente }}</td>
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
const correo = ref('')
const { $apiClient } = useNuxtApp()

const fetchClientes = async (path = '/') => {
    try {
        const response = await $apiClient.get(API_ROUTES.CLIENTE + path)
        clientes.value = response.data
        console.log('Clientes obtenidos con :' + path)
    } catch (error) {
        console.error('Error al obtener clientes:', error)
        console.log(error.response?.data?.message || 'Error al obtener clientes')
        alert('No se pudieron cargar los clientes.')
    }
}

const buscarClientePorCorreo = async () => {
    if (!correo.value) {
        alert('Por favor ingresa un correo para buscar.')
        return
    }
    try {
        const response = await $apiClient.get(API_ROUTES.CLIENTE + '/correo', {
            params: { correo: correo.value }
        })
 
        clientes.value = response.data ? [response.data] : []
        console.log('Cliente obtenido con el correo:', correo.value)
        console.log('Clientes obtenidos:', clientes.value)
    } catch (error) {
        console.error('Error al buscar cliente por correo:', error)
        alert('No se pudo encontrar el cliente.')
    }
}

onMounted(() => {
    fetchClientes('/')
})
</script>