package likelion13th.shop.DTO.response;

import likelion13th.shop.domain.Item;
import lombok.Getter;

@Getter
public class ItemResponse {
    // 상품은 이런 속성을 가지고 있다
    private Long id;
    private String  itemName;
    private int     itemPrice;
    private int     itemRemain;
    private String  itemImg;
    private String  itemDetail;

    // 속성에 값을 채워넣는 부분
    public ItemResponse(Item item) {
        this.id = item.getId();
        this.itemName = item.getItemName(); // getter도 카멜 케이스로 변경
        this.itemPrice = item.getItemPrice();
        this.itemRemain = item.getItemRemain();
        this.itemImg = item.getItemImg();
        this.itemDetail = item.getItemDetail();
    }
}

// ItemResponse.java
// 클라이언트에 상품 정보를 전달하기 위한 응답 DTO
// Item 엔티티의 필요한 필드만 포함하여 API 응답을 정의
// 엔티티를 직접 노출하지 않아 데이터 안정성과 유연성을 확보
