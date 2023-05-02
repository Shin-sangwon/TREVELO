package com.ssafy.enjoytrip.global.service;

public interface EmailService {

    void sendTempPassword(String email, String tempPassword);
    void sendLoginId(String email, String loginId);
}
