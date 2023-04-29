package com.ssafy.enjoytrip.member.model.dto;

import com.ssafy.enjoytrip.member.model.entity.Grade;
import com.ssafy.enjoytrip.member.model.entity.Role;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    private String loginId;
    private String loginPassword;
    private String name;
    private LocalDateTime birthday;
    private String email;
    private Role role;
    private Grade grade;
    private long mileage;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;

    public void encodePassword(BCryptPasswordEncoder encoder) {
        this.loginPassword = encoder.encode(this.loginPassword);
    }
    public static MemberDto from(MemberJoinDto memberJoinDto) {
        return MemberDto.builder()
                        .loginId(memberJoinDto.getLoginId())
                        .loginPassword(memberJoinDto.getLoginPassword())
                        .name(memberJoinDto.getName())
                        .birthday(memberJoinDto.getBirthday())
                        .email(memberJoinDto.getEmail())
                        .role(Role.MEMBER)
                        .grade(Grade.GENERAL)
                        .mileage(0)
                        .createdat(memberJoinDto.getCreatedat())
                        .updatedat(memberJoinDto.getUpdatedat())
                        .build();
    }

}
