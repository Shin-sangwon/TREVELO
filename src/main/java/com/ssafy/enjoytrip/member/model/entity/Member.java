package com.ssafy.enjoytrip.member.model.entity;

import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.dto.MemberLoginDto;
import com.ssafy.enjoytrip.member.model.dto.MemberUpdateDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Alias("member")
public class Member implements UserDetails {

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
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public void encodePassword(BCryptPasswordEncoder encoder) {
        this.loginPassword = encoder.encode(this.loginPassword);
    }

    public void updatePassword(String password) {
        this.loginPassword = password;
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
                     .email(memberUpdateDto.getEmail())
                     .build();
    }

    @Override
    public String toString() {
        return "Member{" +
            "id=" + id +
            ", loginId='" + loginId + '\'' +
            ", loginPassword='" + loginPassword + '\'' +
            ", name='" + name + '\'' +
            ", birthday=" + birthday +
            ", email='" + email + '\'' +
            ", role=" + role +
            ", grade=" + grade +
            ", mileage=" + mileage +
            ", createdat=" + createdat +
            ", updatedat=" + updatedat +
            '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        this.authorities.add(new SimpleGrantedAuthority("MEMBER"));

        return authorities;
    }

    public boolean hasAuthority(String auth) {
        return authorities.stream().anyMatch(x -> x.getAuthority().equals(auth));
    }

    public void grantSellerAuthority() {
        this.authorities.add(new SimpleGrantedAuthority("SELLER"));
        this.role = Role.SELLER;
    }

    @Override
    public String getPassword() {
        return this.loginPassword;
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
