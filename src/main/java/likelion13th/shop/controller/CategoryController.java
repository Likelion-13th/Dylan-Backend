package likelion13th.shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import likelion13th.shop.DTO.request.CategoryRequestDto;
import likelion13th.shop.DTO.response.CategoryResponseDto;
import likelion13th.shop.DTO.response.ItemResponseDto;
import likelion13th.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    // OrderController.java의 패턴을 그대로 따라하세요!
    // @Operation 어노테이션으로 Swagger 문서화
    // ApiResponse.onSuccess/onFailure로 일관된 응답
    private final CategoryService categoryService;

    // GET /categories - 전체 카테고리 조회
    @Operation(summary = "전체 카테고리 조회")
    @GetMapping
    public List<CategoryResponseDto> getAll() {
        return categoryService.findAll();
    }

    // POST /categories - 카테고리 생성
    @Operation(summary = "카테고리 생성")
    @PostMapping
    public void create(@RequestBody CategoryRequestDto request) {
        categoryService.create(request);
    }

    // GET /categories/{id} - 개별 카테고리 조회
    @Operation(summary = "개별 카테고리 조회")
    @GetMapping("/{id}")
    public CategoryResponseDto getOne(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    // PUT /categories/{id} - 카테고리 수정
    @Operation(summary = "카테고리 수정")
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody CategoryRequestDto request) {
        categoryService.update(id, request);
    }

    // DELETE /categories/{id} - 카테고리 삭제
    @Operation(summary = "카테고리 삭제")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    // GET /categories/{categoryId}/items - 카테고리별 상품 조회
    @Operation(summary = "카테고리별 상품 조회")
    @GetMapping("/{categoryId}/items")
    public List<ItemResponseDto> getItemsByCategory(@PathVariable Long categoryId) {
        return categoryService.findItemsByCategory(categoryId);
    }
}

// Service한테 "해줘"하고 요청을 던져주는 코드
