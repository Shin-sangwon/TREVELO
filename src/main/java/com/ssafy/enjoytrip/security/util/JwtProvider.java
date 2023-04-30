package com.ssafy.enjoytrip.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    public static String getLoginId(String token, String secretKey) {

        return Jwts.parserBuilder()
                   .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .get("loginId", String.class);
    }

    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parserBuilder()
                   .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getExpiration()
                   .before(new Date());
    }

    public static String createToken(String loginId, String SecretKey, long expireTimeMs) {
        Claims claims = Jwts.claims();
        claims.put("loginId", loginId);

        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                   .signWith(Keys.hmacShaKeyFor(SecretKey.getBytes(StandardCharsets.UTF_8)),
                       SignatureAlgorithm.HS256)
                   .compact();

    }

}
