package likelion13th.shop.DTO.response;

import likelion13th.shop.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserInfoResponse {
    // 유저는 이런 속성을 가지고 있다
    private Long id;
    private String username;
    private Long recentlyUsed;
    private String phoneNumber;

    // 속성에 값을 채워넣는 부분
    public UserInfoResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.recentlyUsed = user.getRecentlyUsed();
        this.phoneNumber = user.getPhoneNumber();
    }
}

// UserInfoResponse.java
// 클라이언트에 유저 정보를 전달하기 위한 응답 DTO
// User 엔티티의 필요한 필드만 포함하여 API 응답을 정의
// 엔티티를 직접 노출하지 않아 데이터 안정성과 유연성을 확보
