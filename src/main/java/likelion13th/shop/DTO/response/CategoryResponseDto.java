package likelion13th.shop.DTO.response;

import likelion13th.shop.domain.Category;
import lombok.Getter;


// 클라이언트에 응답을 보낼 때 사용하는 DTO 클래스 선언
@Getter
public class CategoryResponseDto {
    // 카테고리는 이런 속성을 가지고 있다
    private Long id;
    private String category_name;
    private String category_level;

    // 속성에 값을 채워넣는 부분
    public CategoryResponseDto(Category category) {
        this.id = category.getId();
        this.category_name = category.getCategory_name();
        this.category_level = category.getCategory_level();
    }
}

/*
    Django의 forms.py, serializers.py 같은 역할
    요청 받을 때, 응답 보낼 때 데이터 포맷 정의
    Entity를 직접 노출하지 않고 필요한 필드만 주고받음
 */
