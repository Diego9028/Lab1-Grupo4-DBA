// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  css: ['bootstrap/dist/css/bootstrap.min.css',
    '@/src/assets/styles.css',
  ],
  compatibilityDate: '2024-11-01',
  pages: true,
  devtools: { enabled: true },
  runtimeConfig: {
    public: {
      backendServer: process.env.BACKEND_SERVER,
      backendPort: process.env.BACKEND_PORT
    }
  }
})

