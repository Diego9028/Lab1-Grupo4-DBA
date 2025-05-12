package delivery.demo.controllers;

import delivery.demo.config.LoginRequest;
import delivery.demo.config.RegisterRequest;
import delivery.demo.config.TokenResponse;
import delivery.demo.services.TokenSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class TokenController {

    private final TokenSevice tokenSevice;

    @PostMapping("/registrar")
    public ResponseEntity<TokenResponse> registrarCliente(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(tokenSevice.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> loginCliente(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(tokenSevice.login(request));
    }

    @PostMapping("/refresh")
    public TokenResponse refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication
    ) {
        return tokenSevice.refreshToken(authentication);
    }
}
