package likelion13th.shop.login.auth.jwt;

import likelion13th.shop.domain.Address;
import likelion13th.shop.domain.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails {
    private Long userId;
    private String providerId;
    private String usernickname;
    private Address address;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.userId = user.getId();
        this.providerId = user.getProviderId();
        this.usernickname = user.getUsernickname();
        this.address = user.getAddress();
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public CustomUserDetails(String providerId, String usernickname,
                             Collection<? extends GrantedAuthority> authorities) {
        this.userId = null;
        this.providerId = providerId;
        this.usernickname = null;
        this.address = null;
        this.authorities = authorities;
    }

    public static CustomUserDetails fromEntity(User entity) {
        return CustomUserDetails.builder()
                .userId(entity.getId())
                .providerId(entity.getProviderId())
                .usernickname(entity.getUsernickname())
                .address(entity.getAddress())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .id(this.userId)
                .providerId(this.providerId)
                .usernickname(this.usernickname)
                .address(this.address)
                .build();
    }

    @Override
    public String getUsername() {
        return this.providerId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.authorities != null && this.authorities.isEmpty()) {
            return this.authorities;
        }
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        // 소셜 로그인은 비밀번호를 사용하지 않음
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 정책 사용 시 실제 값으로 교체
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 잠금 정책 사용 시 실제 값으로 교체
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명(비밀번호) 만료 정책 사용 시 실제 값으로 교체
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 활성/비활성 정책 사용 시 실제 값으로 교체 (예: 탈퇴/정지 사용자)
        return true;
    }
}

/*
1) 왜 필요한가
- CustomUserDetails로 UserDetails를 구현해 Spring Security가 인증된 사용자의 정보(로그인, 회원가입 유저)를
  저장하고 인가처리(@Authenticationprincipal 등)를 할 수 있게 함


2) 없으면/틀리면?
- SecurityContext에 사용자 인증 정보가 저장되지 않아 인가처리 어노테이션(@Authenticationprincipal 등) 사용 불가


3) 핵심 설계 포인트(코드와 함께)
- User 엔터티 기반 커스텀 객체 생성자

- CustomUserDetails fromEntity, User toEntity:
  fromEntity - Entity -> Dto로 변환해 SecurityContext에 사용자 정보를 저장할 때 사용
  toEntity - Dto -> Entity로 변환해 사용자 정보를 바탕으로 DB 조회/조작할 때 사용

- @Override 어노테이션이 붙은 함수들:
  UserDetails를 구현할 떄 반드시 return 값을 설정해 주어야하는 부분들
*/