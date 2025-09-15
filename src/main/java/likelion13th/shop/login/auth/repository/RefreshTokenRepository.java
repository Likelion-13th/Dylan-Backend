package likelion13th.shop.login.auth.repository;

import likelion13th.shop.domain.User;
import likelion13th.shop.login.auth.jwt.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * RefreshToken 저장소
 * - 사용자(User)와 1:1로 매핑된 RefreshToken을 조회/삭제한다.
 * - Spring Data JPA의 파생 쿼리와 @Query(JPQL)를 혼용.
 */
@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    // 사용자 엔티티로 RefreshToken 한 건을 조회
    // - 존재하지 않을 수 있으므로 Optional로 감싼다.
    Optional<RefreshToken> findByUser(User user);

    // 사용자 기준으로 RefreshToken을 삭제 (JPQL 직접 정의)
    // - @Modifying: DML(DELETE/UPDATE) 쿼리임을 명시
    // - 트랜잭션 경계(@Transactional)는 서비스 레이어에서 감싸는 것을 권장
    @Modifying
    @Query("DELETE FROM RefreshToken rt WHERE rt.user = :user")
    void deleteByUser(@Param("user") User user);
}

/*
1) 왜 필요한가
- 특정 사용자의 refreshToken을 조회하거나 삭제할 떄 사용


2) 없으면/틀리면?
- 사용자의 refreshToken을 조회, 삭제할 수 없어 로그아웃, 회원탈퇴(DB에 더미 데이터 쌓임), 재로그인 처리에
  애로 사항 발생


3) 핵심 설계 포인트(코드와 함께)
- @Modifying @Query("DELETE ..."):
  Spring Data JPA는 기본적으로 @Query를 SELECT 쿼리로만 인식 -> @Modifying: DML(DELETE/UPDATE) 쿼리임을 명시 (없으면 에러)
  JPQL(Java Persistence Query Language) 문법으로 작성된 쿼리 -> 사용자와 연결된 refreshToken만 삭제
  @Param("user"): JPQL의 :user에 메서드의 user 매개변수를 매핑
*/