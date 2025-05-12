<template>
  <div class="container">
        <h1 class="title">Lista de Pedidos</h1>
        <table class="table">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>IdPedido</th>
                    <th>IdProducto(s)</th>
                    <th>Cantidad(es)</th>
                    <th>Precio</th>
                    <th>Entregado</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="pedido in pedidos" :key="pedido.id_pedido">
                    <td>{{ formatDate(pedido.hora_pedido) }}</td>
                    <td>{{ pedido.id_pedido}}</td>
                    <td>{{ listaProductos.join(", ") }}</td>
                    <td>{{ listaCantidades.join(", ") }}</td>
                    <td>{{ precioTotal }}</td>
                    <td>{{ detallePedido[pedido.id_detalle_pedido]?.entregado ? 'Sí' : 'No' }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
    import { ref, onMounted } from 'vue'
    import { useNuxtApp } from '#app'
    import { jwtDecode } from 'jwt-decode' 
    import API_ROUTES from '../src/api-routes.js'

    const pedidos = ref([])
    const detallePedido = ref([])
    const pedidoProducto = ref([])
    const listaProductos = ref([])
    const listaCantidades = ref([])
    const precio = ref(0)
    const precioTotal = ref(0)
    const { $apiClient } = useNuxtApp()

    const fetchPrecio = async (id_producto_servicio) => {
        try {
            const response = await $apiClient.get(`${API_ROUTES.PRODUCTO_SERVICIO}/id`, {params: { id: id_producto_servicio }})
            precio.value = response.data.precio
        } catch (error) {
            console.error('Error al obtener precio total del pedido:', error)
            alert(error.response?.data?.message || 'No se pudo cargar el precio total del pedido.')
        }
    }

    const fetchPedidoProducto = async (idPedido) => {
        try {
            const response = await $apiClient.get(`${API_ROUTES.PEDIDO_PRODUCTO}/id`, {params: { id: idPedido }})
            for (const producto of response.data) {
                listaProductos.value.push(producto.id_producto_servicio)
                listaCantidades.value.push(producto.cantidad)
            }
            pedidoProducto.value[idPedido] = response.data;
        } catch (error) {
            console.error('Error al obtener productos del pedido:', error)
            alert(error.response?.data?.message || 'No se pudieron cargar los productos del pedido.')
        }
    }

    const fetchDetallePedido = async (idPedido) => {
        try {
            const response = await $apiClient.get(`${API_ROUTES.DETALLE_PEDIDO}/id`, {params: { id: idPedido }})
            detallePedido.value[idPedido] = response.data;
        } catch (error) {
            console.error('Error al obtener detalle del pedido:', error)
            alert(error.response?.data?.message || 'No se pudo cargar el detalle del pedido.')
        }
    }

    const fetchPedidos = async (idCliente) => {
    try {
        const response = await $apiClient.get(`${API_ROUTES.PEDIDO}/cliente/${idCliente}`)
        pedidos.value = response.data

        for (const pedido of pedidos.value) {
            await fetchDetallePedido(pedido.id_detalle_pedido);
            await fetchPedidoProducto(pedido.id_pedido);

            for (let i = 0; i < listaProductos.value.length; i++) {
                await fetchPrecio(listaProductos.value[i]);
                precioTotal.value += precio.value * listaCantidades.value[i];
            }
        }
    } catch (error) {
        console.error('Error al obtener pedidos:', error)
        alert(error.response?.data?.message || 'No se pudieron cargar los pedidos.')
    }
}

    const formatDate = (timestamp) => {
    const date = new Date(timestamp)
    return date.toLocaleDateString('es-ES') 
}

    onMounted(() => {

    const token = localStorage.getItem('access_token')
    if (!token) {
        alert('No estás autenticado.')
        return
    }

    const decodedToken = jwtDecode(token)
    const idCliente = decodedToken.jti 

    fetchPedidos(idCliente)
})
</script>