package org.domain.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.domain.dto.CredentialDto;
import org.domain.repository.CompanyRepository;
import org.domain.repository.ConsumerRepository;
import org.domain.repository.RepresentativeRepository;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;

@Service
public class Authentication {

    private final byte[] SECRET = UUID.randomUUID().toString().getBytes();

    public String login(CredentialDto credentialDto, Object repository) throws Exception {
        if (repository instanceof RepresentativeRepository) {
            ((RepresentativeRepository) repository)
                    .findByUsernameAndPassword(credentialDto.getUsername(), credentialDto.getPassword())
                    .orElseThrow(() -> new Exception("Invalid credentials"));
        } else if (repository instanceof ConsumerRepository) {
            ((ConsumerRepository) repository)
                    .findByUsernameAndPassword(credentialDto.getUsername(), credentialDto.getPassword())
                    .orElseThrow(() -> new Exception("Invalid credentials"));
        } else if (repository instanceof CompanyRepository) {
            ((CompanyRepository) repository)
                    .findByUsernameAndPassword(credentialDto.getUsername(), credentialDto.getPassword())
                    .orElseThrow(() -> new Exception("Invalid credentials"));
        }
        return assignToken(credentialDto);
    }

    public String validateAuthorization(String token, Object repository) throws Exception {
        String username = parseToken(token);
        if (repository instanceof RepresentativeRepository) {
            ((RepresentativeRepository) repository)
                    .findByUsername(username)
                    .orElseThrow(() -> new Exception("Invalid token"));
        } else if (repository instanceof ConsumerRepository) {
            ((ConsumerRepository) repository)
                    .findByUsername(username)
                    .orElseThrow(() -> new Exception("Invalid token"));
        } else if (repository instanceof CompanyRepository) {
            ((CompanyRepository) repository)
                    .findByUsername(username)
                    .orElseThrow(() -> new Exception("Invalid token"));
        }
        return username;
    }

    private String assignToken(CredentialDto credentialDto) {
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

    private String parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getId();
    }
}
