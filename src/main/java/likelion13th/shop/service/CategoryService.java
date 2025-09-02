package likelion13th.shop.service;

import likelion13th.shop.DTO.response.ItemResponse;
import likelion13th.shop.domain.Category;
import likelion13th.shop.domain.Item;
import likelion13th.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<ItemResponse> findItemsByCategoryName(String categoryName) {
        // 1. 카테고리 이름으로 Category 엔터티를 찾음
        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new NoSuchElementException("카테고리를 찾을 수 없습니다: " + categoryName));

        List<Item> items = category.getItems();

        // 2. ItemCategory 리스트에서 Item 엔티티를 추출하고 DTO로 변환
        return items.stream()
                .map(ItemResponse::from)     // Item 엔티티를 ItemResponse DTO로 변환
                .collect(Collectors.toList());
    }
}

// CategoryService.java
// 카테고리 이름으로 상품 목록을 조회하는 핵심 비즈니스 로직을 담당하는 서비스
// 데이터 조회 과정에서 예외 처리, 데이터 변환 등을 수행
