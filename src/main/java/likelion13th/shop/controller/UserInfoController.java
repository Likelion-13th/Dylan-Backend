package likelion13th.shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import likelion13th.shop.DTO.request.UserInfoRequestDto;
import likelion13th.shop.DTO.response.UserInfoResponseDto;
import likelion13th.shop.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    // GET /user/me - 내 정보 조회
    @GetMapping("/me")
    @Operation(summary = "내 정보 조회 - 후에 me를 id로 변경 예정")
    public UserInfoResponseDto getMyInfo(@PathVariable Long id) {
        return userInfoService.getMyInfo(id);
    }

    // PUT /user/me - 내 정보 수정
    @PutMapping("/me")
    @Operation(summary = "내 정보 수정")
    public void updateMyInfo(@PathVariable Long id,
                             @RequestBody UserInfoRequestDto requestDto) {
        userInfoService.updateMyInfo(id, requestDto);
    }

    // GET /user/me/mileage - 내 마일리지 조회
    @GetMapping("/me/mileage")
    @Operation(summary = "내 마일리지 조회")
    public Long getMyMileage(@PathVariable Long id) {
        return userInfoService.getMyMileage(id);
    }
}

// Service한테 "해줘"하고 요청을 던져주는 코드