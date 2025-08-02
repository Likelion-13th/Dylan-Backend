package likelion13th.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // 이건 엔터티다 선언
@Getter // getter 메소드를 사용할 수 있게 설정
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동 생성 (JPA를 사용하기 위해서 필요)
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
}

// User의 model을 정의
// ERD를 참고해서 User 테이블의 속성을 정의