export default defineNuxtRouteMiddleware((to, from) => {
    if(typeof window === 'undefined') {
        console.warn('localStorage no está disponible en el servidor.')
        return
    }
    const token = localStorage.getItem('token')
    if (!token && to.path !== '/login' && to.path !== '/register') {
      return navigateTo('/login')
    }
  })