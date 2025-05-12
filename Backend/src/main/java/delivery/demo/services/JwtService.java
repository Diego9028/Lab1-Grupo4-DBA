package delivery.demo.services;

import delivery.demo.entities.ClienteEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${delivery.security.jwt.secret-key}")
    private String secretKey;

    @Value("${delivery.security.jwt.expiration-time}")
    private Long jwtExpiration;

    public String generateToken(final ClienteEntity cliente) {
        return Jwts.builder()
                .setSubject(cliente.getCorreo())
                .claim("id", cliente.getId_cliente().toString())
                .claim("name", cliente.getNombre())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey())
                .compact();
    }

    public Claims extractAll(final String token) {
        try{
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    } catch (SecurityException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Token inválido o malformado", e);
        }
    }

    public String extractUsername(final String token) {
        return extractAll(token).getSubject();
    }

    public boolean isTokenValid(final String token) {
        try {
            final Claims claims = extractAll(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
