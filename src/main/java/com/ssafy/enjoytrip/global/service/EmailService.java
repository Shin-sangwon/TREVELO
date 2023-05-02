package com.ssafy.enjoytrip.global.service;

public interface EmailService {
    
    void sendTempPassword(String email, String tempPassword);
}
