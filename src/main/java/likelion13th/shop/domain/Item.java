package likelion13th.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity // 이건 엔터티다 선언
@Getter // getter 메소드를 사용할 수 있게 설정
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동 생성 (JPA를 사용하기 위해서 필요)
public class Item {

    // 기본 키, id 숫자 자동 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String  itemName;

    @Column(name = "item_price", nullable = false)
    private int     itemPrice;

    @Column(name = "item_remain", nullable = false)
    private int     itemRemain;

    @Column(name = "item_img", nullable = false)
    private String  itemImg;

    @Column(name = "item_detail", nullable = false)
    private String  itemDetail;

    @Column(nullable = false)
    private boolean isNew = false;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}

// Item.java
// item의 model을 정의
// ERD를 참고해서 Item 테이블의 속성을 정의
// Category와의 N:M 관계 설정을 위해 categories 속성 추가
