package delivery.demo.Config;

import delivery.demo.entities.ClienteEntity;
import delivery.demo.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final ClienteRepository clienteRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            final ClienteEntity cliente = clienteRepository.findByCorreo(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Cliente not found"));
            return org.springframework.security.core.userdetails.User
                    .builder()
                    .username(cliente.getCorreo())
                    .password(cliente.getPassword())
                    .build();
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //Se indica el servicio que se encarga de buscar el usuario, osea, el como Spring lo puede hacer
        provider.setUserDetailsService(userDetailsService());
        //Se indica como se encripta la contraseña
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
