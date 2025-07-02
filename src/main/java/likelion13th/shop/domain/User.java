package likelion13th.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private Long mileage;
    private String address;
    private String providerId;
    private Long recentlyUsed;
    private Long zipCode;
    private String addressDetail;
    private String phoneNumber;

    // 유저 객체 생성자
    public User(String username, Long mileage, String address,
                String providerId, Long recentlyUsed, Long zipCode,
                String addressDetail, String phoneNumber) {
        this.username = username;
        this.mileage = mileage;
        this.address = address;
        this.providerId = providerId;
        this.recentlyUsed = recentlyUsed;
        this.zipCode = zipCode;
        this.addressDetail = addressDetail;
        this.phoneNumber = phoneNumber;
    }

    // 유저 객체의 데이터를 수정할 때 사용하는 메서드
    public void update(String username, Long mileage,
                       String providerId, Long recentlyUsed,
                       String phoneNumber) {
        this.username = username;
        this.mileage = mileage;
        this.providerId = providerId;
        this.recentlyUsed = recentlyUsed;
        this.phoneNumber = phoneNumber;
    }

    // 유저 객체의 주소 데이터를 수정할 때 사용하는 메서드
    public void updateAddress(String address, String addressDetail, Long zipCode) {
        this.address = address;
        this.addressDetail = addressDetail;
        this.zipCode = zipCode;
    }
}

// User의 model을 정의
// ERD를 참고해서 User 테이블의 속성을 정의