package org.service.provider.service.service;

import org.domain.dto.CredentialDto;
import org.domain.dto.EmailDto;
import org.domain.model.ServiceProvider;
import org.domain.repository.ConsumerRepository;
import org.domain.service.EmailService;
import org.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public String logIn(CredentialDto credentialDto) throws Exception {
        return userService.logInAsServiceProvider(credentialDto);
    }

    public String logOut(String token) throws Exception {
        return userService.logOutAsServiceProvider(token);
    }

    public String sendEmail(EmailDto emailDto, String token) throws Exception {
        ServiceProvider serviceProvider = userService.findServiceProvider(token);
        if (serviceProvider.getAuthorization()) {
            return emailService.send(consumerRepository.findBySubscription(true), emailDto);
        }
        throw new Exception("Service provider is not authorized to send email");
    }
}
