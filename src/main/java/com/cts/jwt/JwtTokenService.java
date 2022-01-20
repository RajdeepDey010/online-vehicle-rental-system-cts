package com.cts.jwt;

<<<<<<< HEAD
import com.cts.entitiy.User;
=======
import com.cts.entities.User;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenService implements Serializable {


<<<<<<< HEAD
    @Value("${jwt.expirytime}")
    public long JWT_TOKEN_VALIDITY;
=======
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1

    @Value("${jwt.secret}")
    private String secret;

    //retrieve username from jwt token
<<<<<<< HEAD
    public String getEmailFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get("email");
    }

    public String getUserTypeFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get("usertype");
=======
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
<<<<<<< HEAD
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
=======
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
<<<<<<< HEAD
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims;
=======
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
<<<<<<< HEAD
        claims.put("email", user.getEmail());
        claims.put("usertype", user.getUserType().name());
        return doGenerateToken(claims);
=======
        return doGenerateToken(claims, user.getEmail());
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
<<<<<<< HEAD
    private String doGenerateToken(Map<String, Object> claims) {

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
=======
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
<<<<<<< HEAD
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
=======
    public Boolean validateToken(String token, User user) {
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getEmail()) && !isTokenExpired(token));
>>>>>>> c02b3eea0e412ff309ea6021d4452863302d61c1
    }
}
