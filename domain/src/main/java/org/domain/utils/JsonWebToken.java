package org.domain.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.domain.dto.CredentialDto;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;

@Service
public class JsonWebToken {

    private final byte[] SECRET = UUID.randomUUID().toString().getBytes();

    public String build(CredentialDto credentialDto) {
        return "{\"token\": \"" +
                Jwts.builder()
                        .setId(credentialDto.getUsername())
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setSubject("my-manuals")
                        .setIssuer("my-manuals")
                        .signWith(new SecretKeySpec(SECRET, SignatureAlgorithm.HS256.getJcaName()), SignatureAlgorithm.HS256)
                        .setExpiration(new Date(System.currentTimeMillis() * 1000))
                        .compact()
                + "\"}";
    }

    public Claims parse(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
