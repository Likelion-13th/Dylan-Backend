package likelion13th.shop.repository;

import likelion13th.shop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // OrderRepository.java의 패턴을 그대로 따라하세요!
    // JPA 쿼리 메서드 활용
}

/*
    Django의 Model.objects.all(), filter() 같은 ORM 기능만 따로 뺀 파일
    Spring에서는 DB 접근을 JPA Repository로 분리해서 담당함
 */