package likelion13th.shop.repository;

import likelion13th.shop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

// UserRepository.java
// User 엔티티를 관리하는 리포지토리
// 현재 구현할 API들은 이 기본 기능으로 충분하여 별도의 커스텀 메서드는 필요하지 않음