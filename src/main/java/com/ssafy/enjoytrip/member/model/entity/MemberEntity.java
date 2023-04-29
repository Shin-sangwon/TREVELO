package com.ssafy.enjoytrip.member.model.entity;

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
public class MemberEntity {

    private long id;
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

}
