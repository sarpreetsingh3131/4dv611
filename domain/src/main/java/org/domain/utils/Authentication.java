package org.domain.utils;

import org.domain.repository.CompanyRepository;
import org.domain.repository.ConsumerRepository;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Service;
import sun.plugin.dom.exception.InvalidAccessException;

import javax.security.sasl.AuthenticationException;
import java.util.Optional;

@Service
public class Authentication {

    private String PASSWORD = "mymanuals";

    public String companyLogin(Credentials credentials, CompanyRepository repository) {
        Optional
                .of(repository.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword()))
                .orElseThrow(() -> new InvalidAccessException("Invalid credentials"));
        return encrypt(credentials.getUsername());
    }

    public String consumerLogin(Credentials credentials, ConsumerRepository repository) {
        Optional
                .of(repository.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword()))
                .orElseThrow(() -> new InvalidAccessException("Invalid credentials"));
        return encrypt(credentials.getUsername());
    }

    public String validateCompanyAuthorization(String token, CompanyRepository repository) throws AuthenticationException {
        String username = decrypt(token);
        Optional
                .of(repository.findByUsername(username))
                .orElseThrow(() -> new AuthenticationException("Invalid token"));
        return username;
    }

    public String validateConsumerAuthorization(String token, ConsumerRepository repository) throws AuthenticationException {
        String username = decrypt(token);
        Optional
                .of(repository.findByUsername(username))
                .orElseThrow(() -> new AuthenticationException("Invalid token"));
        return username;
    }

    private String encrypt(String username) {
        StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setPassword(PASSWORD);
        return "{\"token\": \"" + stringEncryptor.encrypt(username) + "\"}";
    }

    private String decrypt(String token) {
        StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setPassword(PASSWORD);
        return stringEncryptor.decrypt(token);
    }
}
