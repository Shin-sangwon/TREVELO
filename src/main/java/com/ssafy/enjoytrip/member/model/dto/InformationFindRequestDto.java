package com.ssafy.enjoytrip.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class InformationFindRequestDto {

    private String loginId;
    private String email;
}
