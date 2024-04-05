package wise.wisewomenhackathon.config;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import java.util.Date;

import static wise.wisewomenhackathon.config.JwtConstants.JWT_EXPIRATION;
import static wise.wisewomenhackathon.config.JwtConstants.JWT_SECRET;

@Component
public class JwtGenerator {

    SignatureAlgorithm sa = SignatureAlgorithm.HS512;

    public String generateToken(Authentication authentication, String userId) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + JWT_EXPIRATION);
        String token = Jwts.builder()
                .setSubject(username)
                .setId(userId)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(sa, JWT_SECRET)
                .compact();
        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(JWT_SECRET)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
        }
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getUserIdFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getId();
    }
}
