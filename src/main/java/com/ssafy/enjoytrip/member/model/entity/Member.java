package com.ssafy.enjoytrip.member.model.entity;

import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.dto.MemberLoginDto;
import com.ssafy.enjoytrip.member.model.dto.MemberUpdateDto;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Alias("member")
public class Member {

    private Long id;
    private String loginId;
    private String loginPassword;
    private String name;
    private LocalDateTime birthday;
    private String email;
    private Role role;
    private Grade grade;
    private Long mileage;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;

    public void encodePassword(BCryptPasswordEncoder encoder) {
        this.loginPassword = encoder.encode(this.loginPassword);
    }

    public static Member from(MemberJoinDto memberJoinDto) {
        return Member.builder()
                     .loginId(memberJoinDto.getLoginId())
                     .loginPassword(memberJoinDto.getLoginPassword())
                     .name(memberJoinDto.getName())
                     .birthday(memberJoinDto.getBirthday())
                     .email(memberJoinDto.getEmail())
                     .role(Role.MEMBER)
                     .grade(Grade.GENERAL)
                     .mileage(0L)
                     .createdat(memberJoinDto.getCreatedat())
                     .updatedat(memberJoinDto.getUpdatedat())
                     .build();
    }

    public static Member from(MemberLoginDto memberLoginDto) {
        return Member.builder()
                     .loginId(memberLoginDto.getLoginId())
                     .loginPassword(memberLoginDto.getLoginPassword())
                     .build();
    }

    public static Member from(MemberUpdateDto memberUpdateDto) {
        return Member.builder()
                     .loginId(memberUpdateDto.getLoginId())
                     .loginPassword(memberUpdateDto.getLoginPassword())
                     .name(memberUpdateDto.getName())
                     .birthday(memberUpdateDto.getBirthday())
                     .email(memberUpdateDto.getEmail())
                     .build();
    }
}
