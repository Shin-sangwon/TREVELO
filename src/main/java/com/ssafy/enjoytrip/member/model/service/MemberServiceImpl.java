package com.ssafy.enjoytrip.member.model.service;

import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.global.service.EmailService;
import com.ssafy.enjoytrip.member.exception.MemberException;
import com.ssafy.enjoytrip.member.model.dto.MemberJoinDto;
import com.ssafy.enjoytrip.member.model.dto.MemberLoginDto;
import com.ssafy.enjoytrip.member.model.dto.MemberUpdateDto;
import com.ssafy.enjoytrip.member.model.dto.InformationFindRequestDto;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;
import com.ssafy.enjoytrip.security.util.JwtProvider;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder encoder;
    private final EmailService emailService;

    @Value("${jwt.token.secret}")
    private String SecretKey;
    private final Long expireTimeMs = 1000 * 60 * 60 * 60L;

    @Transactional
    @Override
    public int join(MemberJoinDto memberJoinDto) throws Exception {

        if (joinDuplicatedCheck(memberJoinDto.getLoginId())) {
            throw new MemberException(ErrorCode.LOGIN_ID_DUPLICATED, "이미 존재하는 ID 입니다.");
        }

        Member member = Member.from(memberJoinDto);
        member.encodePassword(encoder);

        return memberMapper.join(member);
    }

    @Transactional(readOnly = true)
    public boolean joinDuplicatedCheck(String loginId) throws SQLException {

        return memberMapper.joinDuplicatedCheck(loginId);
    }

    @Transactional(readOnly = true)
    @Override
    public String login(MemberLoginDto memberLoginDto) {

        Optional<Member> member = memberMapper.findByLoginId(memberLoginDto.getLoginId());
        // 1. id가 없음
        if (!member.isPresent()) {
            throw new MemberException(ErrorCode.LOGIN_ID_NOT_FOUND, "아이디가 존재하지 않습니다.");
        }
        // 2. 비밀번호가 일치하지 않음

        if (!encoder.matches(memberLoginDto.getLoginPassword(), member.get()
                                                                      .getLoginPassword())) {
            throw new MemberException(ErrorCode.INVALID_PASSWORD, "패스워드가 일치하지 않습니다.");
        }

        return JwtProvider.createToken(member.get()
                                             .getLoginId(), SecretKey, expireTimeMs);
    }

    @Transactional(readOnly = true)
    @Override
    public Member findByLoginId(String loginId) {
        return memberMapper.findByLoginId(loginId)
                           .orElseThrow(() -> new MemberException(ErrorCode.LOGIN_ID_NOT_FOUND,
                               "아이디가 존재하지 않습니다."));
    }

    @Transactional
    @Override
    public Member update(MemberUpdateDto memberUpdateDto) throws Exception {

        Member updateDto = Member.from(memberUpdateDto);
        updateDto.encodePassword(encoder);
        memberMapper.update(updateDto);

        return findByLoginId(memberUpdateDto.getLoginId());
    }

    @Transactional
    @Override
    public void signOut(String loginId) {

        memberMapper.signOut(loginId);
    }

    @Transactional
    @Override
    public String findPassword(InformationFindRequestDto informationFindRequestDto) {

        Member member = memberMapper.findByLoginIdAndEmail(informationFindRequestDto)
                                    .orElseThrow(
                                        () -> new MemberException(ErrorCode.LOGIN_ID_NOT_FOUND,
                                            "아이디가 존재하지 않습니다.")
                                    );
        String tempPassword = getTempPassword();

        try {
            emailService.sendTempPassword(member.getEmail(), tempPassword);
        } catch (Exception e) {
            return "이메일 처리 중 에러가 발생했습니다.";
        }
        // 임시 비밀번호로 설정 후 암호화
        member.updatePassword(tempPassword);
        member.encodePassword(encoder);
        // 비밀번호 바꿔서 db에 갱신
        memberMapper.update(member);
        return "가입하신 계정으로 임시 비밀번호를 전송했습니다.";
    }

    @Transactional(readOnly = true)
    @Override
    public String findLoginId(InformationFindRequestDto informationFindRequestDto) {

        Member member = memberMapper.findByLoginIdAndEmail(informationFindRequestDto)
                                    .orElseThrow(
                                        () -> new MemberException(ErrorCode.LOGIN_ID_NOT_FOUND,
                                            "아이디가 존재하지 않습니다.")
                                    );
        try {
            emailService.sendLoginId(member.getEmail(), member.getLoginId());
        } catch (Exception e) {
            return "이메일 처리 중 에러가 발생했습니다.";
        }

        return "가입하신 계정으로 아이디를 전송했습니다.";
    }

    //임시 비밀번호 발급
    private String getTempPassword() {
        // 숫자 0
        final int leftLimit = 48;
        // 소문자 'z'
        final int rightLimit = 122;
        final int passwordLength = 10;

        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                                    .filter(x -> (x <= 57 || x >= 65) && (x <= 90 || x >= 97))
                                    .limit(passwordLength)
                                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                    .toString();
    }

}
