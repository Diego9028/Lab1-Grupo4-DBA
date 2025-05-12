package delivery.demo.services;

import delivery.demo.config.LoginRequest;
import delivery.demo.config.RegisterRequest;
import delivery.demo.config.TokenResponse;
import delivery.demo.entities.ClienteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TokenSevice {

    private final ClienteService clienteService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public TokenResponse register(final RegisterRequest request) {
        var cliente = ClienteEntity.builder()
                .nombre(request.name())
                .correo(request.email())
                .direccion(request.direccion())
                .password(passwordEncoder.encode(request.password()))
                .build();
        clienteService.save(cliente);
        var jwtToken = jwtService.generateToken(cliente);
        return new TokenResponse(jwtToken);
    }

    public TokenResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var cliente = clienteService.findByCorreo(request.email());
        var jwtToken = jwtService.generateToken(cliente);
        return new TokenResponse(jwtToken);
    }

    public TokenResponse refreshToken( String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid Refresh Token");
        }
        final String refreshToken = authHeader.substring(7);
        final String userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail == null) {
            throw new IllegalArgumentException("Invalid Refresh Token");
        }
        final ClienteEntity cliente = clienteService.findByCorreo(userEmail);
        if (!jwtService.isTokenValid(refreshToken)) {
            throw new IllegalArgumentException("Invalid Refresh Token");

        }
        final String accessToken = jwtService.generateToken(cliente);
        return new TokenResponse(accessToken);
    }

}