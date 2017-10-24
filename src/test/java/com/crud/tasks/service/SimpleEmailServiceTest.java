package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService service;

    @Mock
    private JavaMailSender sender;

    @Test
    public void shouldSendCc() throws Exception {
        //Given
        Mail mail = new Mail("test@test.com","test@test.com","Test","TestMessage");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getRecipient());
        mailMessage.setCc(mail.getCc());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //When
        service.send(mail);

        //Then
        verify(sender, times(1)).send(mailMessage);
    }

    @Test
    public void shouldSendNullCc() throws Exception {
        //Given
        Mail mail = new Mail("test@test.com",null,"Test","TestMessage");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getRecipient());
        mailMessage.setCc(mail.getCc()); //needed to avoid null vs nothing conflict
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());

        //When
        service.send(mail);

        //Then
        verify(sender, times(1)).send(mailMessage);
    }

}