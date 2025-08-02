package likelion13th.shop.repository;

import likelion13th.shop.domain.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
    // 특정 categoryId에 속한 ItemCategory 리스트를 조회하는 메서드
    List<ItemCategory> findByCategoryId(Long categoryId);
}

// ItemCategoryRepository.java
// Item과 Category 간의 N:M 관계를 관리하는 리포지토리
// Spring Data JPA의 기본 CRUD 기능 외에, 특정 카테고리에 속한 모든 상품(ItemCategory)을 조회하는 메서드를 정의