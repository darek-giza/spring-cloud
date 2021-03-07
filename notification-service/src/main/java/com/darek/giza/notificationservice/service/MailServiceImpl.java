package com.darek.giza.notificationservice.service;

import com.darek.giza.notificationservice.model.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.host}")
    private String host;

    @Override
    public void sendMail(String email) {

        try
        {
            MimeMessage msg = create(getSession(username, password, host), email);
            log.info("Message is ready");
            Transport.send(msg);
            log.info("Email Sent Successfully!!");
        }
        catch (MessagingException | IOException e) {
            throw AppException.internalServerError("Unknown error during sending the email.", e);
        }
    }

    public static Session getSession(String username, String password, String host){
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.host", host);

        return Session.getInstance(properties,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });


    }

    public static MimeMessage create(Session session, String toEmail) throws MessagingException, UnsupportedEncodingException {
        String body =
            "Welcome to APP !!!,\n" +
                "if you are reading this email, it means that you have successfully logged into the my application.";
        MimeMessage msg = new MimeMessage(session);
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");
        msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));
        msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
        msg.setSubject("Registration for the application", "UTF-8");
        msg.setText(body, "UTF-8");
        msg.setSentDate(new Date());
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        return msg;
    }

}
