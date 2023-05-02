package com.ssafy.enjoytrip.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PasswordFindRequestDto {

    private String loginId;
    private String email;
}
