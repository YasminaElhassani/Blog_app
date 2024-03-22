package io.novelis.novelisblogapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final String secretKey="jskkqsqomqlmqslsllslslsshssjospaapapapap";
    public String generateToken(String userName)
    {
        return  Jwts.builder().subject(userName).signWith(Keys.hmacShaKeyFor(secretKey.getBytes())).compact();
    }
    public String getUsernameFromToken(String token)
    {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes())).build().parseSignedClaims(token).getPayload().getSubject();
    }

}
