package com.alkemy.alkemy.email;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailService {

    @Value("${alkemy.email.sender}")
    private String sender;

    @Value("${alkemy.email.enabled}")
    private Boolean enabled;

    public void sendEmail(String destination) throws IOException {
        if(!enabled) return;
        Email from = new Email(sender);
        String subject = "Welcome to Disney!";
        Email to = new Email(destination);
        Content content = new Content("text/plain", "Welcome to Disney Api! Enjoy your stay!");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

}
