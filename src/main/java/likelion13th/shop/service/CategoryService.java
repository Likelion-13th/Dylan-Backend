package likelion13th.shop.service;

import jakarta.transaction.Transactional;
import likelion13th.shop.DTO.response.CategoryResponseDto;
import likelion13th.shop.DTO.response.ItemResponseDto;
import likelion13th.shop.domain.Category;
import likelion13th.shop.repository.CategoryRepository;
import likelion13th.shop.repository.ItemCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import likelion13th.shop.DTO.request.CategoryRequestDto;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {
    // OrderService.java의 패턴을 그대로 따라하세요!
    // @Transactional 사용으로 데이터 일관성 보장
    // Optional 활용으로 null 안전성 확보
    // Stream API 활용으로 깔끔한 데이터 변환
    private final CategoryRepository categoryRepository;
    private final ItemCategoryRepository itemCategoryRepository;

    // 카테고리 전체 조회
    /*
        categoryRepository.findAll()로 모든 카테고리 데이터를 DB에서 가져옴.
        stream()으로 리스트를 스트림 형태로 바꾸고,
        map(CategoryResponseDto::new)로 각 엔티티를 DTO로 변환하고
        .toList()로 다시 리스트로 바꿈.
     */
    public List<CategoryResponseDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponseDto::new)
                .toList();
    }

    // 카테고리 개별 조회
    // id로 객체 탐색 -> 찾으면 DTO로 변환해 반환 : 없으면 예외 발생
    public CategoryResponseDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 없음"));
        return new CategoryResponseDto(category);
    }

    // 카테고리 생성
    @Transactional
    public void create(CategoryRequestDto request) {
        categoryRepository.save(new Category(request.getCategory_name(), request.getCategory_level()));
    }

    // 카테고리 수정
    // id로 객체 탐색 -> 찾으면 DTO로 변환해 반환 및 수정 : 없으면 예외 발생
    @Transactional
    public void update(Long id, CategoryRequestDto request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("카테고리 없음"));

        category.update(request.getCategory_name(), request.getCategory_level());
    }

    // 카테고리 삭제
    @Transactional
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    // 카테고리 id로 상품 조회
    public List<ItemResponseDto> findItemsByCategory(Long categoryId) {
        return itemCategoryRepository.findByCategory_Id(categoryId).stream()
                .map(ic -> new ItemResponseDto(ic.getItem()))
                .toList();
    }
}

/*
    Django에선 views.py 안에 섞여 있던 비즈니스 로직을 분리한 것
    진짜 핵심 로직 수행 (예: 주문 처리, 마일리지 계산 등)
    Controller는 Service한테 “이거 해줘~”만 말함
 */
