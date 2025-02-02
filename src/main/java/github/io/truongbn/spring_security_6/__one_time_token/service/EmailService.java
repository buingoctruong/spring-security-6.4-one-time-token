package github.io.truongbn.spring_security_6.__one_time_token.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import github.io.truongbn.spring_security_6.__one_time_token.exception.EmailSendException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    @Value("${sendgrid.api-key}")
    private String sendgridApiKey;
    public void sendEmail(final String to, final String subject, final String content) {
        final Email from = new Email("buingoctruong1508@gmail.com");
        final Email toEmail = new Email(to);
        final Content emailContent = new Content("text/plain", content);
        final Mail mail = new Mail(from, subject, toEmail, emailContent);
        // configure SendGrid client
        final SendGrid sg = new SendGrid(sendgridApiKey);
        final Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            log.info("Sending token to email: {}", toEmail);
            Response response = sg.api(request);
            log.info("Token sent successfully to {}, Response: {}", toEmail, response);
        } catch (final IOException ex) {
            throw new EmailSendException("Failed to send the email", ex);
        }
    }
}
