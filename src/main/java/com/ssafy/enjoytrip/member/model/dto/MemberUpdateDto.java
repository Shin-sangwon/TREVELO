package com.ssafy.enjoytrip.member.model.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateDto {
    private String loginId;
    private String loginPassword;
    private String name;
    private LocalDateTime birthday;
    private String email;
}
