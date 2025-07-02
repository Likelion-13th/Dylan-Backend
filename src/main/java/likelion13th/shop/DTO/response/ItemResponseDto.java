package likelion13th.shop.DTO.response;

import likelion13th.shop.domain.Item;
import lombok.Getter;

// 클라이언트에 응답을 보낼 때 사용하는 DTO 클래스 선언
@Getter
public class ItemResponseDto {
    // 상품은 이런 속성을 가지고 있다
    private Long id;
    private String  item_name;
    private int     item_price;
    private int     item_remain;
    private String  item_img;
    private String  item_detail;

    // 속성에 값을 채워넣는 부분
    public ItemResponseDto(Item item) {
        this.id = item.getId();
        this.item_name = item.getItem_name();
        this.item_price = item.getItem_price();
        this.item_remain = item.getItem_remain();
        this.item_img = item.getItem_img();
        this.item_detail = item.getItem_detail();
    }
}

/*
    Django의 forms.py, serializers.py 같은 역할
    요청 받을 때, 응답 보낼 때 데이터 포맷 정의
    Entity를 직접 노출하지 않고 필요한 필드만 주고받음
 */
