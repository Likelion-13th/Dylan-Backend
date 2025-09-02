package likelion13th.shop.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import likelion13th.shop.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemResponse {
    // 상품은 이런 속성을 가지고 있다
    private Long id;
    private String  itemName;
    private int     itemPrice;
    private int     itemRemain;
    private String  itemImg;
    private String  itemDetail;
    private boolean isNew;

    @JsonProperty("isNew")
    public boolean getIsNew() {
        return isNew;
    }

    // 속성에 값을 채워넣는 부분
    public static ItemResponse from(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getItemName(),
                item.getItemPrice(),
                item.getItemRemain(),
                item.getItemImg(),
                item.getItemDetail(),
                item.isNew()
        );
    }
}

// ItemResponse.java
// 클라이언트에 상품 정보를 전달하기 위한 응답 DTO
// Item 엔티티의 필요한 필드만 포함하여 API 응답을 정의
// 엔티티를 직접 노출하지 않아 데이터 안정성과 유연성을 확보
