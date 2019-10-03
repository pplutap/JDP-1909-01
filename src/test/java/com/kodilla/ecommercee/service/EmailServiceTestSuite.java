package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTestSuite {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void should_SendMail() {
        //Given
        Email email = new Email("ecommercee.app@gmail.com", "Test subject", "Test message");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email.getMailTo());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getMessage());

        //When
        emailService.send(email);

        //Then
        verify(javaMailSender).send(mailMessage);
    }

}