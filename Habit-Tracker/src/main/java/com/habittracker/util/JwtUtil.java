package com.habittracker.util;

import io.jsonwebtoken.*;

import static com.habittracker.constants.ApplicationConstants.JWT_SECRET_KEY;
import static com.habittracker.constants.ApplicationConstants.JWT_SECRET_VALUE;
import static com.habittracker.constants.ApplicationConstants.JWT_EXPIRATION_TIME;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final Environment env;

    public String generateToken(final UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date()).expiration(new Date(new Date().getTime() + JWT_EXPIRATION_TIME))
                .signWith(getKey())
                .compact();
    }

    private Key getKey() {
        final String secretKey = env.getProperty(JWT_SECRET_KEY, JWT_SECRET_VALUE);
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(final String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(final String token, final UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(final String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    private Claims extractClaims(final String token) {
        try {
            return Jwts.parser().verifyWith((SecretKey)getKey()).build().parseSignedClaims(token).getPayload();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token expired", e);
        } catch (JwtException e) {
            throw new RuntimeException("Invalid token", e);
        }
    }
}
