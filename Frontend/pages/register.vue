<template>
    <div class="container mt-5">
        <form @submit.prevent="handleRegister" class="form-register">
            <h1 class="h3 mb-3 fw-normal text-center">Registro</h1>
            <div class="form-floating mb-3">
                <input v-model="name" type="text" class="form-control" id="floatingName" placeholder="Name" required />
                <label for="floatingName">Nombre</label>
            </div>
            <div class="form-floating mb-3">
                <input v-model="direccion" type="text" class="form-control" id="floatingDireccion" placeholder="Dirección" required= />
                <label for="floatingDireccion">Dirección</label>
            </div>
            <div class="form-floating mb-3">
                <input v-model="email" type="email" class="form-control" id="floatingEmail" placeholder="Correo Electrónico" required />
                <label for="floatingEmail">Correo Electrónico</label>
            </div>
            <div class="form-floating mb-3">
                <input v-model="password" type="password" class="form-control" id="floatingPassword" placeholder="Contraseña" required />
                <label for="floatingPassword">Contraseña</label>
            </div>
            <button class="btn btn-primary w-100 py-2" type="submit">Registrar</button>
        </form>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useNuxtApp } from '#app'
import API_ROUTES from '../src/api-routes'

const name = ref('')
const direccion = ref('')
const email = ref('')
const password = ref('')
const router = useRouter()
const { $apiClient } = useNuxtApp()

const handleRegister = async () => {
    try {
        const response = await $apiClient.post(API_ROUTES.REGISTER, {
            name: name.value,
            direccion: direccion.value,
            email: email.value,
            password: password.value,
        })
        alert('Registro exitoso, ahora puedes iniciar sesión')
        router.push('/login') // Redirigir al login
    } catch (error) {
        console.error('Error al registrar:', error)
        alert(error.response?.data?.message || 'Error al registrar')
    }
}
</script>

<style scoped>
.container {
    max-width: 400px;
    margin: auto;
}
</style>

/*
register.vue:41 
 POST http://localhost:8090/auth/register 403 (Forbidden)
Promise.then		
handleRegister	@	register.vue:41

register.vue:50 Error al registrar: 
AxiosError {message: 'Request failed with status code 403', name: 'AxiosError', code: 'ERR_BAD_REQUEST', config: {…}, request: XMLHttpRequest, …}
code
: 
"ERR_BAD_REQUEST"
config
: 
adapter
: 
(3) ['xhr', 'http', 'fetch']
allowAbsoluteUrls
: 
true
baseURL
: 
"http://localhost:8090"
data
: 
"{\"name\":\"Duvan\",\"direccion\":\"aldnajkdna\",\"email\":\"z@gmail.com\",\"password\":\"12\"}"
env
: 
Blob
: 
ƒ Blob()
FormData
: 
ƒ FormData()
[[Prototype]]
: 
Object
headers
: 
AxiosHeaders
Accept
: 
"application/json, text/plain, */*"
Content-Type
: 
"application/json"
clear
: 
(...)
concat
: 
(...)
constructor
: 
(...)
delete
: 
(...)
get
: 
(...)
getAccept
: 
(...)
getAcceptEncoding
: 
(...)
getAuthorization
: 
(...)
getContentLength
: 
(...)
getContentType
: 
(...)
getSetCookie
: 
(...)
getUserAgent
: 
(...)
has
: 
(...)
hasAccept
: 
(...)
hasAcceptEncoding
: 
(...)
hasAuthorization
: 
(...)
hasContentLength
: 
(...)
hasContentType
: 
(...)
hasUserAgent
: 
(...)
normalize
: 
(...)
set
: 
(...)
setAccept
: 
(...)
setAcceptEncoding
: 
(...)
setAuthorization
: 
(...)
setContentLength
: 
(...)
setContentType
: 
(...)
setUserAgent
: 
(...)
toJSON
: 
(...)
toString
: 
(...)
Symbol(Symbol.toStringTag)
: 
(...)
[[Prototype]]
: 
Object
maxBodyLength
: 
-1
maxContentLength
: 
-1
method
: 
"post"
timeout
: 
0
transformRequest
: 
[ƒ]
transformResponse
: 
[ƒ]
transitional
: 
clarifyTimeoutError
: 
false
forcedJSONParsing
: 
true
silentJSONParsing
: 
true
[[Prototype]]
: 
Object
url
: 
"/auth/register"
validateStatus
: 
ƒ validateStatus(status)
length
: 
1
name
: 
"validateStatus"
prototype
: 
{}
arguments
: 
(...)
caller
: 
(...)
[[FunctionLocation]]
: 
axios.js?v=5950cd7a:906
[[Prototype]]
: 
ƒ ()
[[Scopes]]
: 
Scopes[2]
xsrfCookieName
: 
"XSRF-TOKEN"
xsrfHeaderName
: 
"X-XSRF-TOKEN"
[[Prototype]]
: 
Object
constructor
: 
ƒ Object()
hasOwnProperty
: 
ƒ hasOwnProperty()
isPrototypeOf
: 
ƒ isPrototypeOf()
propertyIsEnumerable
: 
ƒ propertyIsEnumerable()
toLocaleString
: 
ƒ toLocaleString()
toString
: 
ƒ toString()
valueOf
: 
ƒ valueOf()
__defineGetter__
: 
ƒ __defineGetter__()
__defineSetter__
: 
ƒ __defineSetter__()
__lookupGetter__
: 
ƒ __lookupGetter__()
__lookupSetter__
: 
ƒ __lookupSetter__()
__proto__
: 
(...)
get __proto__
: 
ƒ __proto__()
set __proto__
: 
ƒ __proto__()
message
: 
"Request failed with status code 403"
name
: 
"AxiosError"
request
: 
XMLHttpRequest
onabort
: 
ƒ handleAbort()
onerror
: 
ƒ handleError()
onload
: 
null
onloadend
: 
ƒ onloadend()
onloadstart
: 
null
onprogress
: 
null
onreadystatechange
: 
null
ontimeout
: 
ƒ handleTimeout()
readyState
: 
4
response
: 
""
responseText
: 
""
responseType
: 
""
responseURL
: 
"http://localhost:8090/auth/register"
responseXML
: 
null
status
: 
403
statusText
: 
""
timeout
: 
0
upload
: 
XMLHttpRequestUpload {onloadstart: null, onprogress: null, onabort: null, onerror: null, onload: null, …}
withCredentials
: 
false
[[Prototype]]
: 
XMLHttpRequest
response
: 
{data: '', status: 403, statusText: '', headers: AxiosHeaders, config: {…}, …}
status
: 
403
stack
: 
"AxiosError: Request failed with status code 403\n    at settle (http://localhost:3000/_nuxt/node_modules/.cache/vite/client/deps/axios.js?v=5950cd7a:1229:12)\n    at XMLHttpRequest.onloadend (http://localhost:3000/_nuxt/node_modules/.cache/vite/client/deps/axios.js?v=5950cd7a:1561:7)\n    at Axios.request (http://localhost:3000/_nuxt/node_modules/.cache/vite/client/deps/axios.js?v=5950cd7a:2119:41)\n    at async handleRegister (http://localhost:3000/_nuxt/pages/register.vue:22:26)"
[[Prototype]]
: 
Error
handleRegister	@	register.vue:50

*/

/*
package delivery.demo.config;

import delivery.demo.repositories.TokenRepository;
import delivery.demo.entities.TokenEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final TokenRepository tokenRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults()) // Enable CORS
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/auth/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/auth/logout")
                                .addLogoutHandler(this::logout)
                                .logoutSuccessHandler((request, response, authentication) ->
                                        SecurityContextHolder.clearContext())
                )
        ;
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); //Cambia según tu frontend
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private void logout(
            final HttpServletRequest request, final HttpServletResponse response,
            final Authentication authentication
    ) {

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("invalid token");
        }

        final String jwt = authHeader.substring(7);
        final TokenEntity storedToken = tokenRepository.findByToken(jwt)
                .orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
            SecurityContextHolder.clearContext();
        }
    }
}

*/