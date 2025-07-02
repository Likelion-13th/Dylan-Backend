package likelion13th.shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import likelion13th.shop.DTO.request.UserAddressRequestDto;
import likelion13th.shop.DTO.response.UserAddressResponseDto;
import likelion13th.shop.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/me")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService userAddressService;

    // GET /user/me/address - 내 주소 조회
    @GetMapping("/address")
    @Operation(summary = "내 주소 조회 - 후에 me를 id로 변경 예정")
    public UserAddressResponseDto getMyAddress(@PathVariable Long id) {
        return userAddressService.getMyAddress(id);
    }

    // PUT /user/me/address - 내 주소 수정
    @PutMapping("/address")
    @Operation(summary = "내 주소 수정")
    public void updateMyAddress(@PathVariable Long id,
                             @RequestBody UserAddressRequestDto requestDto) {
        userAddressService.updateMyAddress(id, requestDto);
    }
}

// Service한테 "해줘"하고 요청을 던져주는 코드