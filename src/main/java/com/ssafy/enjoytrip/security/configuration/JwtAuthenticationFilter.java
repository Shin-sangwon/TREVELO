package com.ssafy.enjoytrip.security.configuration;

import com.ssafy.enjoytrip.global.ErrorCode;
import com.ssafy.enjoytrip.member.exception.MemberException;
import com.ssafy.enjoytrip.member.model.entity.Role;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import com.ssafy.enjoytrip.security.util.JwtProvider;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final MemberService memberService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : '{}'", authorization);

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.error("authentication is null");
            filterChain.doFilter(request, response);
            return;
        }

        // get token
        String token = authorization.split(" ")[1];

        // is expired?
        if(JwtProvider.isExpired(token, secretKey)) {
            log.error("authentication is expired");
            filterChain.doFilter(request, response);
            return;
        }

        String loginId = JwtProvider.getLoginId(token, secretKey);
        Role role;
        try {
            role = memberService.findByLoginId(loginId).getRole();
        } catch (Exception e) {
            throw new MemberException(ErrorCode.LOGIN_ID_NOT_FOUND, "존재하지 않는 회원입니다.");
        }
        //권한 부여하기
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            loginId, null, Collections.singletonList(new SimpleGrantedAuthority(role.name())));

        //Detail
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext()
                             .setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
