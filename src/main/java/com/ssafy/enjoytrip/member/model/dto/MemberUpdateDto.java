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
    // TODO : BIRTHDAY 추가하기 (직렬화, 역직렬화)
    private String loginId;
    private String loginPassword;
    private String name;
    private String email;
}
