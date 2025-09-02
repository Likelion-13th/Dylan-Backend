package likelion13th.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Category {

    // 기본 키, id 숫자 자동 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_level", nullable = false)
    private String categoryLevel;

    @ManyToMany
    @JsonIgnore //무한 루프 방지  (카테고리 내부에서 items 목록을 JSON 변환에서 제외)
    @JoinTable(name = "category_item", //중간 테이블 자동으로 생성
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();
}

// Category.java
// category의 model을 정의
// ERD를 참고해서 카테고리 id, category_name, category_level 속성을 정의
// Item과의 N:M 관계 설정을 위해 items 속성 추가