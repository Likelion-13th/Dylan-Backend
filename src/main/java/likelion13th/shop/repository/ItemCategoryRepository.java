package likelion13th.shop.repository;

import likelion13th.shop.domain.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
    List<ItemCategory> findByCategory_Id(Long categoryId);
}

/*
    Django의 Model.objects.all(), filter() 같은 ORM 기능만 따로 뺀 파일
    Spring에서는 DB 접근을 JPA Repository로 분리해서 담당함
 */