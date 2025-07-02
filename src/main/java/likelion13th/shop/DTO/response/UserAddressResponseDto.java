package likelion13th.shop.DTO.response;

import lombok.Getter;
import likelion13th.shop.domain.User;

// 클라이언트에 응답을 보낼 때 사용하는 DTO 클래스 선언
@Getter
public class UserAddressResponseDto {
    private final String address;
    private final String addressDetail;
    private final Long zipCode;

    // 속성에 값을 채워넣는 부분
    public UserAddressResponseDto(User user) {
        this.address = user.getAddress();
        this.addressDetail = user.getAddressDetail();
        this.zipCode = user.getZipCode();
    }
}

/*
    Django의 forms.py, serializers.py 같은 역할
    요청 받을 때, 응답 보낼 때 데이터 포맷 정의
    Entity를 직접 노출하지 않고 필요한 필드만 주고받음
 */