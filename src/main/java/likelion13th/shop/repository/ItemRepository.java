package likelion13th.shop.repository;

import likelion13th.shop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> { }

/*
    Django의 Model.objects.all(), filter() 같은 ORM 기능만 따로 뺀 파일
    Spring에서는 DB 접근을 JPA Repository로 분리해서 담당함
 */