<template>
    <div class="container">
        <h1 class="title">Lista de Desempeño</h1>
        <table class="table">
            <thead>
                <tr>
                    <th>TotalPuntos</th>
                    <th>Promedio</th>
                    <th>IdRepartidor</th>
                    <th>Nombre</th>
                    <th>TotalPedidos</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="repartidor in desempenos" :key="repartidor.id_repartidor">
                    <td>{{ repartidor.total_puntos }}</td>
                    <td>{{ repartidor.promedio }}</td>
                    <td>{{ repartidor.id_repartidor }}</td>
                    <td>{{ repartidor.nombre }}</td>
                    <td>{{ repartidor.total_pedidos }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useNuxtApp } from '#app'
import API_ROUTES from '../src/api-routes.js'

const desempenos = ref([])
const { $apiClient } = useNuxtApp()

const fetchdesempenos = async () => {
    try {

        const response = await $apiClient.get(API_ROUTES.DESEMPENO)
        desempenos.value = response.data
    } catch (error) {
        console.error('Error al obtener desempenos:', error)
        console.log(error.response?.data?.message || 'Error al obtener desempenos')
        alert('No se pudieron cargar los desempenos.')
    }
}

onMounted(() => {
    fetchdesempenos()
})
</script>