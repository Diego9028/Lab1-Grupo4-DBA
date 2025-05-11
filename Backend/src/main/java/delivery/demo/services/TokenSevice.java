package delivery.demo.services;

import delivery.demo.config.LoginRequest;
import delivery.demo.config.RegisterRequest;
import delivery.demo.config.TokenResponse;
import delivery.demo.entities.ClienteEntity;
import delivery.demo.entities.TokenEntity;
import delivery.demo.repositories.TokenRepositoryImp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenSevice {

    private final ClienteService clienteService;
    private final TokenRepositoryImp tokenRepositoryImp;
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
        var savedCliente = clienteService.save(cliente);
        var jwtToken = jwtService.generateToken(cliente);
        var refreshToken = jwtService.generateRefreshToken(cliente);
        saveClienteToken(savedCliente, jwtToken);
        return new TokenResponse(jwtToken, refreshToken);
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
        var refreshToken = jwtService.generateRefreshToken(cliente);
        // Con "revokeAllClienteTokens" se eliminan todos los tokens que tenia el cliente antes, pero
        // Si se deja, el cliente no puede volver a logearse con el mismo token
        revokeAllClienteTokens(cliente);
        saveClienteToken(cliente, jwtToken);
        return new TokenResponse(jwtToken, refreshToken);
    }

    public void saveClienteToken(final ClienteEntity cliente, final String jwtToken) {
        var token = TokenEntity.builder()
                .cliente_id(cliente.getId_cliente())
                .token(jwtToken)
                .token_type(TokenEntity.TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepositoryImp.save(token);
    }

    private void revokeAllClienteTokens(final ClienteEntity cliente) {
        final List<TokenEntity> validClienteTokens = tokenRepositoryImp
                .findAllValidTokenByUser(cliente.getId_cliente());
        if (!validClienteTokens.isEmpty()) {
            validClienteTokens.forEach(t -> {
                t.setExpired(true);
                t.setRevoked(true);
            });
        }
        tokenRepositoryImp.saveAll(validClienteTokens);
    }

    public TokenResponse refreshToken(final String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid Refresh Token");
        }
        final String refreshToken = authHeader.substring(7);
        final String userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail == null) {
            throw new IllegalArgumentException("Invalid Refresh Token");
        }
        final ClienteEntity cliente = clienteService.findByCorreo(userEmail);
        if (!jwtService.isTokenValid(refreshToken, cliente)) {
            throw new IllegalArgumentException("Invalid Refresh Token");

        }
        final String accessToken = jwtService.generateToken(cliente);
        revokeAllClienteTokens(cliente);
        saveClienteToken(cliente, accessToken);
        return new TokenResponse(accessToken, refreshToken);
    }

}