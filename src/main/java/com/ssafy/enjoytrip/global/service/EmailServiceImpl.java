package com.ssafy.enjoytrip.global.service;

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
    public void sendTempPassword(String email, String tempPassword) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String subject = "요청하신 임시 비밀번호입니다.";
        String text = String.format("비밀번호는 '%s'입니다.", tempPassword);
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);

    }

    @Async
    @Override
    public void sendLoginId(String email, String loginId) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String subject = "요청하신 아이디입니다.";
        String text = String.format("아이디는 '%s'입니다.", loginId);
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);

    }
}
