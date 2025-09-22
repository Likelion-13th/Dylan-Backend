package likelion13th.shop.login.auth.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import likelion13th.shop.domain.Address;
import likelion13th.shop.domain.User;
import likelion13th.shop.login.auth.dto.JwtDto;
import likelion13th.shop.login.auth.jwt.CustomUserDetails;
import likelion13th.shop.login.auth.service.JpaUserDetailsManager;
import likelion13th.shop.repository.UserRepository;
import likelion13th.shop.login.service.UserServiceLogin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final UserRepository userRepository;
    private final JpaUserDetailsManager jpaUserDetailsManager;
    private final UserServiceLogin userServiceLogin;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException {
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

        String providerId = (String) oAuth2User.getAttributes().get("provider_id");
        String nickname = (String) oAuth2User.getAttributes().get("nickname");

        if (!jpaUserDetailsManager.userExists(providerId)) {
            User newUser = User.builder()
                    .providerId(providerId)
                    .usernickname(nickname)
                    .deletable(true)
                    .build();
            newUser.setAddress(new Address("10540", "경기도 고양시 덕양구 항공대학로 76", "한국항공대학교"));

            CustomUserDetails userDetails = new CustomUserDetails(newUser);
            jpaUserDetailsManager.createUser(userDetails);
            log.info("신규 회원 등록이용");
        } else {
            log.info("기존 회원이용");
        }

        JwtDto jwt = userServiceLogin.jwtMakeSave(providerId);

        String frontendRedirectUri = request.getParameter("redirect_uri");
        List<String> authorizeUris = List.of(
                "http://localhost:8080/"
        );
        if (frontendRedirectUri == null || !authorizeUris.contains(frontendRedirectUri)) {
            frontendRedirectUri = "https://dylan-perfume.netlify.app/";
        }

        String redirectUri = UriComponentsBuilder
                .fromUriString(frontendRedirectUri)
                .queryParam("accessToken", jwt.getAccessToken())
                .build().toUriString();

        log.info("리다이렉트 시켜보아요: {}", frontendRedirectUri);

        response.sendRedirect(redirectUri);
    }
}

/*
1) 왜 필요한가
- OAuth2SuccessHandler로 Spring Security 표준 인터페이스인 AuthenticationSuccessHandler를 구현
- 사용자 로그인 후의 동작을 정의
- 사용자 최초 로그인 여부 판단, DB에 사용자 저장, JWT 발급, uri(프론트엔드)에 accessToken을 담아 리다이렉트 등을 수행


2) 없으면/틀리면?
- 사용자 로그인 후의 동작 처리가 수행되지 않음
  즉, DB에 사용자 정보 자체가 저장되지 않아 회원가입/로그인 기능이 먹통이 됨


3) 핵심 설계 포인트(코드와 함께)
- public void onAuthenticationSuccess:
  해당 함수의 매개변수들(request, response, authentication)은 자동으로 주입됨 -> AuthenticationSuccessHandler를
  구현한 파일이기 때문

- if (!jpaUserDetailsManager.userExists(providerId))...:
  처음 로그인한 사용자면 User 객체를 만들어 DB에 저장, 아니면 넘어감
*/