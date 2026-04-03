package com.mentorplatform.config;

import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String SECRET = "mysecretkeymysecretkeymysecretkey"; // ≥ 32 chars
    private final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .subject(username)                  // ✅ replaces setSubject()
                .claim("role", role)
                .issuedAt(new Date())               // ✅ replaces setIssuedAt()
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 10))  // ✅ replaces setExpiration()
                .signWith(KEY)                      // ✅ algorithm auto-detected in 0.13.0
                .compact();
    }

    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return parseClaims(token).get("role", String.class);
    }

    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }

    // ✅ parserBuilder() replaced by parser() in 0.13.0
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(KEY)                    // ✅ replaces setSigningKey()
                .build()
                .parseSignedClaims(token)           // ✅ replaces parseClaimsJws()
                .getPayload();                      // ✅ replaces getBody()
    }
}