package com.minsang8332.workbenchapi.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expiration.access-token}")
    private long expirationAccessToken;
    @Value("${jwt.expiration.refresh-token}")
    private long expirationRefreshToken;

    public String getAccessToken(UserDetails user) {
        Date expiration = new Date(System.currentTimeMillis() + expirationAccessToken);
        return getToken(user, new HashMap<>(), expiration);
    }

    public String getRefreshToken(UserDetails user) {
        Date expiration = new Date(System.currentTimeMillis() + expirationRefreshToken);
        return getToken(user, new HashMap<>(), expiration);
    }

    private String getToken(UserDetails user, Map<String, Object> extractClaims, Date expiration) {
        return Jwts.builder()
                .claims(extractClaims)
                .subject(user.getUsername())
                .expiration(expiration)
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    private SecretKey getSecretKey() {
        return new SecretKeySpec(
                secretKey.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm()
        );
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    public String getUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
