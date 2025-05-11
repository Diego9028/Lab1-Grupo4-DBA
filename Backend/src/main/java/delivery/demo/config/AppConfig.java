package delivery.demo.config;

import delivery.demo.entities.ClienteEntity;
import delivery.demo.repositories.ClienteRepository;
import delivery.demo.repositories.ClienteRepositoryImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    public UserDetailsService userDetailsService(ClienteRepository clienteRepository) {
        return username -> {
            // Se busca el cliente por su correo
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
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //Se indica el servicio que se encarga de buscar el usuario, osea, el como Spring lo puede hacer
        provider.setUserDetailsService(userDetailsService);
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

    //configuracion con la base de datos
    /*
    @Bean
    public Sql2o sql2o(
            @Value("jdbc:postgresql://localhost:5432/tbd") String dbUrl,
            @Value("${DB_USER}") String dbUser,
            @Value("${DB_PASSWORD}") String dbPass
    ) {
        return new Sql2o(dbUrl, dbUser, dbPass) {
            @Override
            public Connection open() {
                Connection connection = super.open();
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null && auth.isAuthenticated()) {
                    try {
                        ClienteEntity user = (ClienteEntity) auth.getPrincipal();
                        connection.createQuery("SELECT set_config('app.user.id', :user_id, false)")
                                .addParameter("user_id", user.getId_cliente().toString())
                                .executeScalar(String.class);
                    } catch (Exception ignored) {}
                }
                return connection;
            }
        };
    }
    */

    @Bean
    public Sql2o sql2o(
            @Value("jdbc:postgresql://localhost:5432/tbd") String dbUrl,
            @Value("${DB_USER}") String dbUser,
            @Value("${DB_PASSWORD}") String dbPass
    ) {
        org.sql2o.quirks.Quirks quirks = new org.sql2o.quirks.NoQuirks() {
            public String getColumnName(String columnLabel) {
                String[] parts = columnLabel.split("_");
                StringBuilder camelCase = new StringBuilder(parts[0]);
                for (int i = 1; i < parts.length; i++) {
                    camelCase.append(parts[i].substring(0, 1).toUpperCase()).append(parts[i].substring(1));
                }
                return camelCase.toString();
            }
        };
        return new Sql2o(dbUrl, dbUser, dbPass, quirks);
    }

    @Bean
    public ClienteRepository clienteRepository(Sql2o sql2o) {
        return new ClienteRepositoryImp(sql2o);
    }


}
