package likelion13th.shop.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import likelion13th.shop.DTO.request.AddressRequest;
import likelion13th.shop.domain.User;
import likelion13th.shop.login.auth.jwt.CustomUserDetails;
import likelion13th.shop.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import likelion13th.shop.service.UserService;
import likelion13th.shop.DTO.response.UserInfoResponse;
import likelion13th.shop.DTO.response.UserMileageResponse;
import likelion13th.shop.DTO.response.AddressResponse;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "회원 정보", description = "회원 정보 관련 API 입니다.")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserService userService;
    private final UserAddressService userAddressService;

    private Long getCurrentUserId(@AuthenticationPrincipal CustomUserDetails userPrincipal) {
        // userPrincipal에서 현재 로그인한 사용자의 ID를 추출하는 로직
        /*
            일반적으로 Spring Security는 UserDetailsService를 통해 인증된 사용자의 정보를
            UserDetails 인터페이스를 구현한 객체로 반환함.
            이 객체가 @AuthenticationPrincipal에 주입되는 principal 객체가 됨.
            현재 코드에서는 유저id만 추출하면 되기에 UserDetails를 직접 구현하지 않고 principal 객체만 사용
         */
        return userPrincipal.getUserId();
    }

    // 내 정보 조회
    @Operation(summary = "내 프로필 조회", description = "현재 로그인한 사용자의 상세 프로필 정보를 조회합니다.")
    @GetMapping("/profile")
    public ResponseEntity<UserInfoResponse> getMyProfile(@AuthenticationPrincipal CustomUserDetails userPrincipal) {
        Long userId = getCurrentUserId(userPrincipal);
        UserInfoResponse response = userService.getUserInfo(userId);
        return ResponseEntity.ok(response);
    }

    // 내 마일리지 조회
    @Operation(summary = "내 마일리지 조회", description = "현재 로그인한 사용자의 마일리지 정보를 조회합니다.")
    @GetMapping("/mileage")
    public ResponseEntity<UserMileageResponse> getMyMileage(@AuthenticationPrincipal CustomUserDetails userPrincipal) {
        Long userId = getCurrentUserId(userPrincipal);
        UserMileageResponse response = userService.getUserMileage(userId);
        return ResponseEntity.ok(response);
    }

    // 내 주소 조회
    @Operation(summary = "내 주소 저장", description = "현재 로그인한 사용자의 주소 정보를 생성합니다.")
    @PostMapping("/address")
    public ResponseEntity<AddressResponse> getMyAddress(@AuthenticationPrincipal CustomUserDetails userPrincipal,
                                                        @RequestBody AddressRequest request) {
        String providerId = userPrincipal.getProviderId();
        AddressResponse response = userAddressService.saveAddress(providerId, request);
        return ResponseEntity.ok(response);
    }
}

// UserInfoController.java
// @AuthenticationPrincipal를 통해 로그인된 사용자의 유저 정보, 마일리지 정보, 주소 정보를 조회
// HTTP 200 OK와 함께 조회된 데이터를 응답으로 반환