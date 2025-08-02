package likelion13th.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity // 이건 엔터티다 선언
@Getter // getter 메소드를 사용할 수 있게 설정
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동 생성 (JPA를 사용하기 위해서 필요)
public class Item {

    // 기본 키, id 숫자 자동 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  itemName;
    private int     itemPrice;
    private int     itemRemain;
    private String  itemImg;
    private String  itemDetail;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCategory> itemCategories = new ArrayList<>();
}

// Item.java
// item의 model을 정의
// ERD를 참고해서 Item 테이블의 속성을 정의
// Category와의 N:M 관계 설정을 위해 itemCategories 속성 추가
