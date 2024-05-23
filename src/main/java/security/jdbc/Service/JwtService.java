package security.jdbc.Service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import security.jdbc.Model.User;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    private String secret;
    private Integer expiration;
    @Value("${JWT_SECRET}")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Value("${JWT_EXPIRES_IN}")
    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }

    public String generateToken(User user){
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

    /*public Authentication validateTokenAndGetAuthentication(String token) {
        try{
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            if(claims != null && !claims.isEmpty()){
                String username = claims.getSubject();
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, "", null);
                return new UsernamePasswordAuthenticationToken(userDetails, token);
            }
        }catch(Exception e){
        // Manejar errores al validar el token
        }
        return null;
    }*/
}