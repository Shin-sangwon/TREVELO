package com.ssafy.enjoytrip.member.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Deprecated
public class MemberDto {

//    private String loginId;
//    private String loginPassword;
//    private String name;
//    private LocalDateTime birthday;
//    private String email;
//    private Role role;
//    private Grade grade;
//    private long mileage;
//    private LocalDateTime createdat;
//    private LocalDateTime updatedat;
//
//    public void encodePassword(BCryptPasswordEncoder encoder) {
//        this.loginPassword = encoder.encode(this.loginPassword);
//    }
//
//    public static MemberDto from(MemberJoinDto memberJoinDto) {
//        return MemberDto.builder()
//                        .loginId(memberJoinDto.getLoginId())
//                        .loginPassword(memberJoinDto.getLoginPassword())
//                        .name(memberJoinDto.getName())
//                        .birthday(memberJoinDto.getBirthday())
//                        .email(memberJoinDto.getEmail())
//                        .role(Role.MEMBER)
//                        .grade(Grade.GENERAL)
//                        .mileage(0)
//                        .createdat(memberJoinDto.getCreatedat())
//                        .updatedat(memberJoinDto.getUpdatedat())
//                        .build();
//    }
//
//    public static MemberDto from(MemberLoginDto memberLoginDto) {
//        return MemberDto.builder()
//                        .loginId(memberLoginDto.getLoginId())
//                        .loginPassword(memberLoginDto.getLoginPassword())
//                        .build();
//    }

}
