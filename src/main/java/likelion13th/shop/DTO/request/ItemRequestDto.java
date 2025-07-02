package likelion13th.shop.DTO.request;

import lombok.Getter;

// 클라이언트(프론트엔드, Postman 등)에서 서버로 상품 정보를 보낼 때 사용하는 데이터 클래스
@Getter
public class ItemRequestDto {
    private String  item_name;
    private int     item_price;
    private int     item_remain;
    private String  item_img;
    private String  item_detail;
}

// Item 속성의 데이터를 클라이언트에서 보냄