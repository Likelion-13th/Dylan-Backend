package likelion13th.shop.service;

import likelion13th.shop.DTO.response.AddressResponse;
import likelion13th.shop.DTO.response.UserMileageResponse;
import likelion13th.shop.DTO.response.UserInfoResponse;
import likelion13th.shop.domain.User;
import likelion13th.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // 사용자 상세 프로필 조회
    public UserInfoResponse getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다: " + userId));

        // User 엔티티를 UserProfileResponse DTO로 변환
        return new UserInfoResponse(user);
    }

    // 사용자 마일리지 조회
    public UserMileageResponse getUserMileage(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다: " + userId));

        // User 엔티티를 UserMileageResponse DTO로 변환
        return new UserMileageResponse(user);
    }

    // 사용자 주소 조회
    public AddressResponse getUserAddress(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다: " + userId));

        // User 엔티티를 UserAddressResponse DTO로 변환
        return new AddressResponse(user);
    }
}

// UserService.java
// UserRepository를 통해 DB에서 사용자를 조회하고, 이를 응답 DTO로 변환하여 반환하는 로직을 담당
// 사용자가 없을 경우 NoSuchElementException 예외를 발생시켜 클라이언트에게 적절한 응답을 보냄