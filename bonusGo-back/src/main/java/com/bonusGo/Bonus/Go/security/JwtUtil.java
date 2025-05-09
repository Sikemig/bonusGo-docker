package com.bonusGo.Bonus.Go.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    // nuevo el idUsuario
    public String createToken(Authentication authentication, int idUsuario) {
        User userPrincipal = (User) authentication.getPrincipal();

        String rol = userPrincipal.getAuthorities().iterator().next().getAuthority(); // Ej: "ROLE_USER"

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("rol", rol)
                .claim("id", idUsuario)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getRolFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("rol", String.class);
    }

    public String getUsernameFromJwt(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            System.err.println("JWT expirado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("JWT no soportado: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("JWT mal formado: " + e.getMessage());
        } catch (SignatureException e) {
            System.err.println("Firma inválida: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("JWT vacío o nulo: " + e.getMessage());
        }
        return null;
    }

    // metodo nuevo
    public Integer getIdFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("id", Integer.class);
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("Error al validar el JWT: " + e.getMessage());
            return false;
        }
    }
}
