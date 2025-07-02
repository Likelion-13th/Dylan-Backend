package likelion13th.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    // 기본 키, id 숫자 자동 증가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  item_name;
    private int     item_price;
    private int     item_remain;
    private String  item_img;
    private String  item_detail;

    // 상품 객체 생성자
    public Item(String item_name, int item_price, int item_remain, String item_img, String item_detail) {
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_remain = item_remain;
        this.item_img = item_img;
        this.item_detail = item_detail;
    }

    // 상품 객체의 데이터를 수정할 때 사용하는 메서드
    public void update(String item_name, int item_price, int item_remain, String item_img, String item_detail) {
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_remain = item_remain;
        this.item_img = item_img;
        this.item_detail = item_detail;
    }
}

// item의 model을 정의
// ERD를 참고해서 Item 테이블의 속성을 정의
