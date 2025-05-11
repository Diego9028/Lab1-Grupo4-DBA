export default defineNuxtRouteMiddleware((to, from) => {
    if(typeof window === 'undefined') {
        console.warn('localStorage no está disponible en el servidor.')
        return
    }
    const access_token = localStorage.getItem('access_token')
    if (!access_token && to.path !== '/login' && to.path !== '/register') {
      return navigateTo('/login')
    }
  })