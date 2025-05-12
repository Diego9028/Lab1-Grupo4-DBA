<template>
  <div class="container mx-auto p-6">
    <h1 class="title text-2xl font-bold mb-4">Empresas con Mayor Volumen de Paquetes Entregados</h1>

    <table class="table-auto w-full border rounded shadow">
      <thead class="bg-gray-100">
        <tr>
          <th class="px-4 py-2 text-left">Empresa</th>
          <th class="px-4 py-2 text-left">Paquetes Entregados</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="empresa in empresas" :key="empresa.id_empresa_asociada" class="hover:bg-gray-50">
            <td class="border px-4 py-2">{{ empresa.empresa }}</td>
            <td class="border px-4 py-2">{{ empresa.total_entregados }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useNuxtApp } from '#app'
import API_ROUTES from '../src/api-routes.js'

const empresas = ref([])
const { $apiClient } = useNuxtApp()

const fetchEmpresasTop = async () => {
  try {
    const { data } = await $apiClient.get(`${API_ROUTES.EMPRESA}/top-volumen`)
    empresas.value = data
  } catch (error) {
    console.error('Error al obtener empresas:', error)
    alert(error.response?.data?.message || 'No se pudieron cargar los datos de empresas.')
  }
}

onMounted(fetchEmpresasTop)
</script>

<style scoped>
.container {
  max-width: 800px;
}
.title {
  font-size: 1.5rem;
}
.table-auto th,
.table-auto td {
  border-bottom: 1px solid #e5e7eb;
}
</style>
