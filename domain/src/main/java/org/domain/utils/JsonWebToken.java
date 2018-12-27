package org.domain.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.domain.dto.CredentialDto;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.UUID;

@Service
public class JsonWebToken {

    private final byte[] SECRET = UUID.randomUUID().toString().getBytes();
    private HashSet<String> tokens = new LinkedHashSet<>();

    public String assign(CredentialDto credentialDto) {
        String token = Jwts.builder()
                .setId(credentialDto.getUsername())
                .signWith(new SecretKeySpec(SECRET, SignatureAlgorithm.HS256.getJcaName()), SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() * 10000))
                .compact();
        tokens.add(token);
        return "{\"token\":\"" + token + "\"}";
    }

    public String parse(String token) throws Exception {
        isValid(token);
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getId();
    }

    public String revoke(String token) throws Exception {
        isValid(token);
        tokens.remove(token);
        return "{\"message\": \"logged out successfully\"}";
    }

    private void isValid(String token) throws Exception {
        if (!tokens.contains(token)) {
            throw new Exception("invalid token");
        }
    }
}
