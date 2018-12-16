package org.domain.utils;

import java.io.IOException;
import java.util.List;
import com.sendgrid.*;

public class Email {

    //Needs System environment variable
    public String sendEmail(List<String> emailReceivers, String emailSender, String emailSubject, String emailBody ) {
        com.sendgrid.Email from = new com.sendgrid.Email(emailSender);
        Content body = new Content("text/plain", emailBody);
        Mail mail = new Mail(from, emailSubject, from, body);
        Personalization personalization = new Personalization();
        for (String email :
                emailReceivers) {
            personalization.addBcc(new com.sendgrid.Email(email));
        }
        personalization.addTo(new com.sendgrid.Email(emailSender));
        mail.addPersonalization(personalization);

        SendGrid sendGrid = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
//            System.out.println(response.getStatusCode());
//            System.out.println(response.getBody());
//            System.out.println(response.getHeaders());
        } catch (IOException e) {
//            e.printStackTrace();
            return "{\"sent\": \"emails were sent successfully\"}";
        }
        return "{\"sent\": \"emails were not sent successfully\"}";
    }
}
