package likelion13th.shop.repository;

import likelion13th.shop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // providerId(카카오 고유 ID) 기반 조회 (feature/4)
    Optional<User> findByProviderId(String providerId);

    boolean existsByProviderId(String providerId);

    // usernickname(닉네임) 기반 사용자 찾기 (develop)
    //List<User> findByUsernickname(String usernickname);

    // 향후 필요 시 사용할 수 있도록 주석 유지
    //Optional<User> findByKakaoId(String kakaoId);
}

// UserRepository.java
// User 엔티티를 관리하는 리포지토리
// 현재 구현할 API들은 이 기본 기능으로 충분하여 별도의 커스텀 메서드는 필요하지 않음