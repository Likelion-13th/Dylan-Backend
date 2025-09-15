package likelion13th.shop.login.auth.utils;

import io.swagger.v3.oas.annotations.servers.Server;
import likelion13th.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Server
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String providerId = oAuth2User.getAttributes().get("id").toString();

        @SuppressWarnings("unchecked")
        Map<String, Object> properties =
                (Map<String, Object>) oAuth2User.getAttributes().getOrDefault("properties", Collections.emptyMap());
        String nickname = properties.getOrDefault("nickname","카카오사용자").toString();

        Map<String, Object> extendedAttributes = new HashMap<>(oAuth2User.getAttributes());
        extendedAttributes.put("nickname", nickname);
        extendedAttributes.put("provider_id", providerId);

        return new DefaultOAuth2User(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")),
                extendedAttributes, "provider_id"
        );
    }
}

/*
1) 왜 필요한가
- OAuth2UserServiceImpl로 Spring Security 표준 인터페이스인 DefaultOAuth2UserService를 구현
- 외부 OAuth2 제공자에서 받은 사용자 정보를 우리 서비스에서 쓰기 좋은 형태로 확장/변환하는 역할


2) 없으면/틀리면?
- nickname, providerId 값 추출을 못해 사용자 정보 관리가 어려워짐 (동일 정보 유저 존재 가능성있음)
- 권한 부여(ROLE_USER)를 하지 않으면 관리자, 일반 사용자 구분이 힘듦


3) 핵심 설계 포인트(코드와 함께)
- OAuth2User oAuth2User = super.loadUser(userRequest):
  사용자 정보를 가져옴 (다른 파일에서 따로 함수를 호출하지 않아도 가져올 수 있음)

- oAuth2User.getAttributes().get("id").toString(), properties.getOrDefault("nickname","카카오사용자").toString():
  providerId, nickname 추출

- Map<String, Object> extendedAttributes...:
  원본 속성 복사 후 추출한 providerId, nickname를 Map에 저장

- return new DefaultOAuth2User...: DefaultOAuth2User 객체를 새로 만들어 반환
  - Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")): 사용자에게 권한 부여
  - extendedAttributes: 앞에서 만든 Map
  - provider_id: 사용자의 고유 식별자로 사용할 속성
*/