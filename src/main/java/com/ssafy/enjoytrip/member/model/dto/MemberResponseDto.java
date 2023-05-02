package com.ssafy.enjoytrip.member.model.dto;

import com.ssafy.enjoytrip.member.model.entity.Grade;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.member.model.entity.Role;
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
public class MemberResponseDto {

    private Long id;
    private String loginId;
    private String name;
    private LocalDateTime birthday;
    private String email;
    private Role role;
    private Grade grade;
    private Long mileage;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;

    public static MemberResponseDto from(Member member) {
        return MemberResponseDto.builder()
                                .id(member.getId())
                                .loginId(member.getLoginId())
                                .name(member.getName())
                                .birthday(member.getBirthday())
                                .email(member.getEmail())
                                .role(member.getRole())
                                .grade(member.getGrade())
                                .mileage(member.getMileage())
                                .createdat(member.getCreatedat())
                                .updatedat(member.getUpdatedat())
                                .build();
    }
}
