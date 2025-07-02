package likelion13th.shop.service;

import likelion13th.shop.DTO.request.UserInfoRequestDto;
import likelion13th.shop.DTO.response.UserInfoResponseDto;
import likelion13th.shop.domain.User;
import likelion13th.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserRepository userRepository;

    // 내 정보 조회
    public UserInfoResponseDto getMyInfo(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        return new UserInfoResponseDto(user);
    }

    // 내 정보 수정
    public void updateMyInfo(Long id, UserInfoRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        user.update(requestDto.getUsername(), requestDto.getMileage(),
                requestDto.getProviderId(), requestDto.getRecentlyUsed(),
                requestDto.getPhoneNumber());
    }

    // 내 마일리지 조회
    public Long getMyMileage(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        return user.getMileage();
    }
}

/*
    Django에선 views.py 안에 섞여 있던 비즈니스 로직을 분리한 것
    진짜 핵심 로직 수행 (예: 주문 처리, 마일리지 계산 등)
    Controller는 Service한테 “이거 해줘~”만 말함
 */
