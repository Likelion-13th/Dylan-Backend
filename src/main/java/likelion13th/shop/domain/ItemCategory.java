package likelion13th.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // 이건 엔터티다 선언
@Getter // getter 메소드를 사용할 수 있게 설정
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동 생성 (JPA를 사용하기 위해서 필요)
public class ItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}

// ItemCategory.java
// Item과 Category 테이블 간의 다대다(N:M) 관계를 해결하는 중간 테이블 역할을 하는 엔터티
// Item과 Category에 대한 외래 키를 가지고, 두 엔터티를 연결
// 이 엔티티 자체는 별도의 비즈니스 로직을 가지지 않음