package org.domain.utils;

import org.domain.repository.CompanyRepository;
import org.domain.repository.ConsumerRepository;
import org.domain.repository.RepresentativeRepository;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Service;

@Service
public class Authentication {

    private final String PASSWORD = "mymanuals";

    public String companyLogin(Credentials credentials, CompanyRepository repository) throws Exception {
        repository.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword())
                .orElseThrow(() -> new Exception("Invalid credentials"));
        return encrypt(credentials.getUsername());
    }

    public String consumerLogin(Credentials credentials, ConsumerRepository repository) throws Exception {
        repository.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword())
                .orElseThrow(() -> new Exception("Invalid credentials"));
        return encrypt(credentials.getUsername());
    }

    public String representativeLogin(Credentials credentials, RepresentativeRepository repository) throws Exception {
        repository.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword())
                .orElseThrow(() -> new Exception("Invalid credentials"));
        return encrypt(credentials.getUsername());
    }

    public String validateCompanyAuthorization(String token, CompanyRepository repository) throws Exception {
        String username = decrypt(token);
        repository.findByUsername(username)
                .orElseThrow(() -> new Exception("Invalid token"));
        return username;
    }

    public String validateConsumerAuthorization(String token, ConsumerRepository repository) throws Exception {
        String username = decrypt(token);
        repository.findByUsername(username)
                .orElseThrow(() -> new Exception("Invalid token"));
        return username;
    }

    public String validateRepresentativeAuthorization(String token, RepresentativeRepository repository) throws Exception {
        String username = decrypt(token);
        repository.findByUsername(username)
                .orElseThrow(() -> new Exception("Invalid token"));
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
