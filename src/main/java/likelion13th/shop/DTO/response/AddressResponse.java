package likelion13th.shop.DTO.response;

import likelion13th.shop.domain.Address;
import lombok.Getter;

@Getter
public class AddressResponse {
    private String address;
    private String zipcode;
    private String addressDetail;

    public AddressResponse(Address address) {
        this.zipcode = address.getZipcode();
        this.address = address.getAddress();
        this.addressDetail = address.getAddressDetail();
    }
}

// AddressResponse.java
// 클라이언트에 유저의 주소 정보를 전달하기 위한 응답 DTO
// User 엔티티에서 주소와 관련된 필드만 포함하여 API 응답을 정의
// 엔티티를 직접 노출하지 않아 데이터 안정성과 유연성을 확보