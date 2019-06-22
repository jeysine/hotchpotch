package com.jeysine.services.token.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

/**
 * @author yaojx
 * @date 2018-11-30
 */
public class JwtUtils {
    private final static String SECRET_KEY = "glasses";

    private final static long EXPIRE_TIME = 1000 * 60 * 60 * 6;

   /* @Value("${token.sercetKey}")
    public  static String sercetKey;
    @Value("${token.keeptime:30000}")
    public static long keeptime;*/

    public static String generateToken(String id, String issuer, String subject) {
        long ttlMillis = EXPIRE_TIME;
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now);
        if (subject != null) {
            builder.setSubject(subject);
        }
        if (issuer != null) {
            builder.setIssuer(issuer);
        }
        builder.signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    public static Claims verifyToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        return claims;
    }

    public static String updateToken(String token) throws Exception {
        Claims claims = verifyToken(token);
        String id = claims.getId();
        String subject = claims.getSubject();
        String issuer = claims.getIssuer();
        return generateToken(id, issuer, subject);
    }
}
