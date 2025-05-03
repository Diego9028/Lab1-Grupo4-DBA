package delivery.demo.services;

import delivery.demo.entities.ClienteEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${delivery.security.jwt.secret-key}")
    private String secretKey;

    @Value("${delivery.security.jwt.expiration-time}")
    private Long jwtExpiration;

    @Value("${delivery.security.jwt.refresh-token-expiration-time}")
    private Long refreshExpiration;

    public String generateToken(final ClienteEntity cliente) {
        return buildToken(cliente, jwtExpiration);
    }

    public String generateRefreshToken(final ClienteEntity cliente) {
        return buildToken(cliente, refreshExpiration);
    }

    public String extractUsername(final String token) {
        final Claims jwtToken = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return jwtToken.getSubject();
    }

    private String buildToken(final ClienteEntity cliente, final Long expiration) {
        return Jwts.builder()
                .id(cliente.getId().toString())
                .claims(Map.of("name", cliente.getNombre()))
                .setSubject(cliente.getCorreo())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(final String token , final ClienteEntity cliente) {
        final String username = extractUsername(token);
        return (username.equals(cliente.getCorreo()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(final String token) {
        final Claims jwtToken = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return jwtToken.getExpiration();
    }
}
