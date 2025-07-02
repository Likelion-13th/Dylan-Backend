package likelion13th.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class ItemCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // JoinColumn -> DB에 저장될 외래 키 컬럼 이름 (= item_id)
    // Item 엔티티 타입의 자바 객체 필드 (연관된 대상)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // JoinColumn -> DB에 저장될 외래 키 컬럼 이름 (= category_id)
    // Category 엔티티 타입의 자바 객체 필드 (연관된 대상)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public ItemCategory(Item item, Category category) {
        this.item = item;
        this.category = category;
    }
}

// Item과 Category 엔티티의 N:M 관계를 해소하는 엔티티