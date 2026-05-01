package com.example.biblio.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    private static final String SECRET_KEY =
            "your-256-bit-secret-your-256-bit-secret-your-256-bit-secret!!";

    private static final long EXPIRATION_MS = 1000 * 60 * 60 * 24;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String email, String role) {

        System.out.println("=== JWT GENERATE TOKEN ===");
        System.out.println("EMAIL = " + email);
        System.out.println("ROLE BEFORE = " + role);

        Map<String, Object> claims = new HashMap<>();

        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        System.out.println("ROLE AFTER = " + role);

        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        String role = extractClaim(token, claims -> claims.get("role", String.class));
        System.out.println("=== EXTRACT ROLE FROM TOKEN ===");
        System.out.println("ROLE = " + role);
        return role;
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        boolean expired = extractClaim(token, Claims::getExpiration).before(new Date());
        System.out.println("TOKEN EXPIRED = " + expired);
        return expired;
    }
}