package org.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    // Method to generate a JWT token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(username)  // Setting the username as the subject of the token
                .issuedAt(new Date(System.currentTimeMillis()))  // Token creation time
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10-hour validity
                .signWith(SECRET_KEY)  // Signing the token with the secret key
                .compact();
    }

    // Method to extract the username from the JWT token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();  // The subject field contains the username
    }

    // Method to validate the JWT token by checking the username and expiration
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }



    // Method to check if the token has expired
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }



    // Method to extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)  // Use the secret key to verify the signature
                .build()
                .parseSignedClaims(token)  // Parse the claims JWS from the token
                .getPayload();
    }


}
