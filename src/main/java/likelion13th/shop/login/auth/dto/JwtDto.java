package likelion13th.shop.login.auth.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class JwtDto {
    private String accessToken;
    private String refreshToken;

    public JwtDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}

/*
1) 왜 필요한가
- 소셜 로그인 성공 후 프론트엔드로 accessToken, refreshToken을 전달할 떄 사용


2) 없으면/틀리면?
- 프론트엔드로 accessToken, refreshToken이 전달되지 않음
  -> 헤더에 accessToken을 담을 수 없음 -> 사용자 로그인 여부 확인 불가 -> 로그인된 유저만 접근할 수 있는 API에 접근 불가
*/