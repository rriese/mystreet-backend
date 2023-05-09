package com.github.riese.rafael.mystreet.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MailService {
    @Resource
    private JavaMailSender mailSender;

    public void sendEmail(String mail, String subject, String text) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(mail);
        email.setSubject(subject);
        email.setText(text);
        mailSender.send(email);
    }
}
