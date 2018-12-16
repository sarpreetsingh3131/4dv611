package org.domain.service;

import com.sendgrid.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.domain.model.Consumer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private final Email FROM = new Email("sb223ce@student.lnu.se");

    public String send(List<Consumer> consumers, String subject, String body) throws Exception {
        Mail mail = new Mail(FROM, subject, FROM, new Content("text/plain", body));
        Personalization personalization = new Personalization();
        consumers.forEach(consumer -> personalization.addBcc(new Email(consumer.getEmail())));
        personalization.addTo(FROM);
        mail.addPersonalization(personalization);
        SendGrid sendGrid = new SendGrid(getEmailApiKey());
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sendGrid.api(request);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());
        return "{\"message\": \"Sent successfully\"}";
    }

    public String getEmailApiKey() {
        return Dotenv.load()
                .get("email.api.key");
    }
}
