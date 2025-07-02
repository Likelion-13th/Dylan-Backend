package likelion13th.shop.DTO.request;

import lombok.Getter;

// 클라이언트(프론트엔드, Postman 등)에서 서버로 카테고리 정보를 보낼 때 사용하는 데이터 클래스
@Getter
public class CategoryRequestDto {
    private String category_name;
    private String category_level;
}

// category_name, category_level을 클라이언트에서 보냄