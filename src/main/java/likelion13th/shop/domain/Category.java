package likelion13th.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Category {

    // 기본 키, id 숫자 자동 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category_name;
    private String category_level;

    // 카테고리 객체 생성자
    public Category(String category_name, String category_level) {
        this.category_name = category_name;
        this.category_level = category_level;
    }

    // 카테고리 객체의 데이터를 수정할 때 사용하는 메서드
    public void update(String category_name, String category_level) {
        this.category_name = category_name;
        this.category_level = category_level;
    }
}

// category의 model을 정의
// ERD를 참고해서 카테고리 id, category_name, category_level 속성을 정의