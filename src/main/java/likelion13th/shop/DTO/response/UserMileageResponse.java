package likelion13th.shop.DTO.response;

import likelion13th.shop.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserMileageResponse {
    private Long mileage;

    public UserMileageResponse(User user) {
        this.mileage = user.getMileage();
    }
}

// UserMileageResponse.java
// 클라이언트에 유저의 마일리지 정보를 전달하기 위한 응답 DTO
// User 엔티티의 마일리지 필드만 포함하여 API 응답을 정의
// 엔티티를 직접 노출하지 않아 데이터 안정성과 유연성을 확보