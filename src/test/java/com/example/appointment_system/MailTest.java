//package com.example.appointment_system;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//
//@SpringBootTest
//public class MailTest {
//
//	@Autowired
//    private JavaMailSender emailSender;
//	
//	@Test
//    void sendSimpleMessage() {
//
//        SimpleMailMessage message = new SimpleMailMessage(); 
//        message.setTo("entyu61@gmail.com"); 
//        message.setSubject("測試"); 
//        message.setText("內文標題");
//        emailSender.send(message);
//    }
//}
