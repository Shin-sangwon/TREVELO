package com.ssafy.enjoytrip.global.service;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;
    @Async
    @Override
    public void sendTempPassword(String email, String tempPassword) throws UnsupportedEncodingException, MessagingException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String subject = "요청하신 임시 비밀번호입니다.";
        String test = String.format("비밀번호는 '%s'입니다.", tempPassword);
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(tempPassword);
        javaMailSender.send(mailMessage);

    }
}
