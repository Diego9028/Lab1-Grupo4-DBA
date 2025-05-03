// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  css: ['bootstrap/dist/css/bootstrap.min.css'],
  compatibilityDate: '2024-11-01',
  devtools: { enabled: true },
  runtimeConfig: {
    public: {
      backendServer: process.env.BACKEND_SERVER || 'localhost',
      backendPort: process.env.BACKEND_PORT || '8090'
    }
  }
})

