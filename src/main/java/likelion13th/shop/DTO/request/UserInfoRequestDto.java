package likelion13th.shop.DTO.request;

import lombok.Getter;

// 클라이언트(프론트엔드, Postman 등)에서 서버로 카테고리 정보를 보낼 때 사용하는 데이터 클래스
@Getter
public class UserInfoRequestDto {
    private String username;
    private Long mileage;
    private String providerId;
    private Long recentlyUsed;

    // address, zipCode, addressDetail은 UserAddress에서 따로 처리

    private String phoneNumber;
}

// User 속성의 데이터를 클라이언트에서 보냄
