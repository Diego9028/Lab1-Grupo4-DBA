package delivery.demo.controllers;

import delivery.demo.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedido")
@CrossOrigin
@RequiredArgsConstructor
public class PedidoController {

    @Autowired
    private final PedidoService pedidoService;

    @GetMapping("/mas-pedidos-por-categoria")
    public List<Map<String, Object>> obtenerMasPedidosPorCategoriaUltimoMes() {
        return pedidoService.obtenerMasPedidosPorCategoriaUltimoMes();
    }

    @GetMapping("/tiempos-promedio-entrega")
    public List<Map<String, Object>> obtenerTiemposPromedioEntrega() {
        return pedidoService.obtenerTiemposPromedioEntrega();
    }

    @PostMapping("/crear")
    public ResponseEntity<Long> crearPedido(@RequestBody Map<String, Object> body) {
        Long idUrgencia   = ((Number) body.get("idUrgencia")).longValue();
        Long idRepartidor = ((Number) body.get("idRepartidor")).longValue();
        Long idCliente    = ((Number) body.get("idCliente")).longValue();
        Long idMedioPago  = ((Number) body.get("idMedioPago")).longValue();

        @SuppressWarnings("unchecked")
        List<Number> productosRaw = (List<Number>) body.get("productos");
        List<Long> productos = productosRaw.stream().map(Number::longValue).toList();

        @SuppressWarnings("unchecked")
        List<Number> cantidadesRaw = (List<Number>) body.get("cantidades");
        List<Integer> cantidades = cantidadesRaw.stream().map(Number::intValue).toList();

        Long idPedido = pedidoService.registrarPedidoConProductos(
                idUrgencia, idRepartidor, idCliente, idMedioPago, productos, cantidades
        );

        return ResponseEntity.ok(idPedido);
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Map<String, Object>>> obtenerPedidosPorCliente(@PathVariable Long idCliente) {
        List<Map<String, Object>> pedidos = pedidoService.obtenerPedidosPorCliente(idCliente);
        return ResponseEntity.ok(pedidos);
    }

    /*
    * 2025-05-11T15:32:05.814-04:00 DEBUG 24504 --- [demo] [nio-8090-exec-2] o.s.security.web.FilterChainProxy        : Securing GET /error
2025-05-11T15:32:05.814-04:00 DEBUG 24504 --- [demo] [nio-8090-exec-2] o.s.s.w.a.AnonymousAuthenticationFilter  : Set SecurityContextHolder to anonymous SecurityContext
2025-05-11T15:32:05.814-04:00 DEBUG 24504 --- [demo] [nio-8090-exec-2] o.s.s.w.a.Http403ForbiddenEntryPoint     : Pre-authenticated entry point called. Rejecting access
2025-05-11T15:32:25.157-04:00 DEBUG 24504 --- [demo] [nio-8090-exec-9] o.s.security.web.FilterChainProxy        : Securing OPTIONS /pedido/cliente/11
2025-05-11T15:32:25.161-04:00 DEBUG 24504 --- [demo] [io-8090-exec-10] o.s.security.web.FilterChainProxy        : Securing GET /pedido/cliente/11
2025-05-11T15:32:25.163-04:00 ERROR 24504 --- [demo] [io-8090-exec-10] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception

io.jsonwebtoken.ExpiredJwtException: JWT expired 5418162 milliseconds ago at 2025-05-11T18:02:07.000Z. Current time: 2025-05-11T19:32:25.162Z. Allowed clock skew: 0 milliseconds.
	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:682) ~[jjwt-impl-0.12.5.jar:0.12.5]
	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:362) ~[jjwt-impl-0.12.5.jar:0.12.5]
	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:94) ~[jjwt-impl-0.12.5.jar:0.12.5]
	at io.jsonwebtoken.impl.io.AbstractParser.parse(AbstractParser.java:36) ~[jjwt-impl-0.12.5.jar:0.12.5]
	at io.jsonwebtoken.impl.io.AbstractParser.parse(AbstractParser.java:29) ~[jjwt-impl-0.12.5.jar:0.12.5]
	at io.jsonwebtoken.impl.DefaultJwtParser.parseSignedClaims(DefaultJwtParser.java:821) ~[jjwt-impl-0.12.5.jar:0.12.5]
	at io.jsonwebtoken.impl.DefaultJwtParser.parseClaimsJws(DefaultJwtParser.java:797) ~[jjwt-impl-0.12.5.jar:0.12.5]
	at delivery.demo.services.JwtService.extractUsername(JwtService.java:39) ~[classes/:na]
	at delivery.demo.config.JwtAuthFilter.doFilterInternal(JwtAuthFilter.java:51) ~[classes/:na]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:107) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:93) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.context.SecurityContextHolderFilter.doFilter(SecurityContextHolderFilter.java:82) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.context.SecurityContextHolderFilter.doFilter(SecurityContextHolderFilter.java:69) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:62) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.session.DisableEncodeUrlFilter.doFilterInternal(DisableEncodeUrlFilter.java:42) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:233) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:191) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.web.servlet.handler.HandlerMappingIntrospector.lambda$createCacheFilter$3(HandlerMappingIntrospector.java:243) ~[spring-webmvc-6.2.5.jar:6.2.5]
	at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.web.filter.CompositeFilter.doFilter(CompositeFilter.java:74) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.security.config.annotation.web.configuration.WebMvcSecurityConfiguration$CompositeFilterChainProxy.doFilter(WebMvcSecurityConfiguration.java:238) ~[spring-security-config-6.4.4.jar:6.4.4]
	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:362) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:278) ~[spring-web-6.2.5.jar:6.2.5]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:483) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:397) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:905) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1743) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1190) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at java.base/java.lang.Thread.run(Thread.java:840) ~[na:na]

2025-05-11T15:32:25.164-04:00 DEBUG 24504 --- [demo] [io-8090-exec-10] o.s.security.web.FilterChainProxy        : Securing GET /error
2025-05-11T15:32:25.164-04:00 DEBUG 24504 --- [demo] [io-8090-exec-10] o.s.s.w.a.AnonymousAuthenticationFilter  : Set SecurityContextHolder to anonymous SecurityContext
2025-05-11T15:32:25.164-04:00 DEBUG 24504 --- [demo] [io-8090-exec-10] o.s.s.w.a.Http403ForbiddenEntryPoint     : Pre-authenticated entry point called. Rejecting access
2025-05-11T15:35:35.071-04:00 DEBUG 24504 --- [demo] [nio-8090-exec-4] o.s.security.web.FilterChainProxy        : Securing OPTIONS /pedido/cliente/11
2025-05-11T15:35:35.073-04:00 DEBUG 24504 --- [demo] [nio-8090-exec-3] o.s.security.web.FilterChainProxy        : Securing GET /pedido/cliente/11
2025-05-11T15:35:35.073-04:00 ERROR 24504 --- [demo] [nio-8090-exec-3] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception

io.jsonwebtoken.ExpiredJwtException: JWT expired 5608073 milliseconds ago at 2025-05-11T18:02:07.000Z. Current time: 2025-05-11T19:35:35.073Z. Allowed clock skew: 0 milliseconds.
	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:682) ~[jjwt-impl-0.12.5.jar:0.12.5]
	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:362) ~[jjwt-impl-0.12.5.jar:0.12.5]
	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:94) ~[jjwt-impl-0.12.5.jar:0.12.5]
	at io.jsonwebtoken.impl.io.AbstractParser.parse(AbstractParser.java:36) ~[jjwt-impl-0.12.5.jar:0.12.5]
	at io.jsonwebtoken.impl.io.AbstractParser.parse(AbstractParser.java:29) ~[jjwt-impl-0.12.5.jar:0.12.5]
	at io.jsonwebtoken.impl.DefaultJwtParser.parseSignedClaims(DefaultJwtParser.java:821) ~[jjwt-impl-0.12.5.jar:0.12.5]
	at io.jsonwebtoken.impl.DefaultJwtParser.parseClaimsJws(DefaultJwtParser.java:797) ~[jjwt-impl-0.12.5.jar:0.12.5]
	at delivery.demo.services.JwtService.extractUsername(JwtService.java:39) ~[classes/:na]
	at delivery.demo.config.JwtAuthFilter.doFilterInternal(JwtAuthFilter.java:51) ~[classes/:na]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:107) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:93) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.context.SecurityContextHolderFilter.doFilter(SecurityContextHolderFilter.java:82) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.context.SecurityContextHolderFilter.doFilter(SecurityContextHolderFilter.java:69) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:62) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.session.DisableEncodeUrlFilter.doFilterInternal(DisableEncodeUrlFilter.java:42) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:233) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:191) ~[spring-security-web-6.4.4.jar:6.4.4]
	at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.web.servlet.handler.HandlerMappingIntrospector.lambda$createCacheFilter$3(HandlerMappingIntrospector.java:243) ~[spring-webmvc-6.2.5.jar:6.2.5]
	at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.web.filter.CompositeFilter.doFilter(CompositeFilter.java:74) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.security.config.annotation.web.configuration.WebMvcSecurityConfiguration$CompositeFilterChainProxy.doFilter(WebMvcSecurityConfiguration.java:238) ~[spring-security-config-6.4.4.jar:6.4.4]
	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:362) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:278) ~[spring-web-6.2.5.jar:6.2.5]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[spring-web-6.2.5.jar:6.2.5]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.2.5.jar:6.2.5]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:483) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:397) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:905) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1743) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1190) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63) ~[tomcat-embed-core-10.1.39.jar:10.1.39]
	at java.base/java.lang.Thread.run(Thread.java:840) ~[na:na]

2025-05-11T15:35:35.074-04:00 DEBUG 24504 --- [demo] [nio-8090-exec-3] o.s.security.web.FilterChainProxy        : Securing GET /error
2025-05-11T15:35:35.074-04:00 DEBUG 24504 --- [demo] [nio-8090-exec-3] o.s.s.w.a.AnonymousAuthenticationFilter  : Set SecurityContextHolder to anonymous SecurityContext
2025-05-11T15:35:35.074-04:00 DEBUG 24504 --- [demo] [nio-8090-exec-3] o.s.s.w.a.Http403ForbiddenEntryPoint     : Pre-authenticated entry point called. Rejecting access*/

    @PostMapping("/confirmar")
    public ResponseEntity<String> confirmarPedido(@RequestBody Map<String, Object> body) {
        Long idPedido = ((Number) body.get("idPedido")).longValue();
        pedidoService.confirmarPedido(idPedido);
        return ResponseEntity.ok("Pedido confirmado");
    }

    @PostMapping("/cambiar-estado")
    public ResponseEntity<Long> cambiarEstado(@RequestBody Map<String, Object> body) {
        Long idPedido = ((Number) body.get("idPedido")).longValue();
        boolean nuevoEstado = (boolean) body.get("nuevoEstado");

        boolean resultado = pedidoService.cambiarEstadoPedidoPorFuncion(idPedido, nuevoEstado);

        return ResponseEntity.ok(resultado ? idPedido : -1L);
    }
}
