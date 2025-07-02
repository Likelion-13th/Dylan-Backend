package likelion13th.shop.service;

import likelion13th.shop.DTO.request.UserAddressRequestDto;
import likelion13th.shop.DTO.response.UserAddressResponseDto;
import likelion13th.shop.domain.User;
import likelion13th.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAddressService {

    private final UserRepository userRepository;

    // 내 주소 조회
    public UserAddressResponseDto getMyAddress(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        return new UserAddressResponseDto(user);
    }

    // 내 주소 수정
    public void updateMyAddress(Long id, UserAddressRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        user.updateAddress(requestDto.getAddress(), requestDto.getAddressDetail(), requestDto.getZipCode());
    }
}

/*
    Django에선 views.py 안에 섞여 있던 비즈니스 로직을 분리한 것
    진짜 핵심 로직 수행 (예: 주문 처리, 마일리지 계산 등)
    Controller는 Service한테 “이거 해줘~”만 말함
 */