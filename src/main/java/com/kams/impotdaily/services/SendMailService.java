/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kams.impotdaily.services;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author macbook
 */
@Service
public class SendMailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(String toEMail, String body, String subject) {

        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("kamabu.mirembe@gmail.com");
        message.setFrom("info@wazait.com");
        message.setTo(toEMail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail Send ...");

    }
    
    public void sendSimpleMailHtml(String toEMail, String body, String subject) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            message.setContent(body, "text/html");
            message.setFrom("info@wazait.com");
            helper.setTo(toEMail);
            helper.setSubject(subject);
            mailSender.send(message);
            System.out.println("HTML mail Send ...");
        } catch (MessagingException ex) {
            Logger.getLogger(SendMailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
