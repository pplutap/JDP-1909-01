package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(final Email email) {
        try {
            javaMailSender.send(createMailMessage(email));
        } catch (MailException e) {
            System.err.println("Email has not been sent due to the following exception:" + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    private SimpleMailMessage createMailMessage(final Email email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email.getMailTo());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getMessage());
        return mailMessage;
    }

}
