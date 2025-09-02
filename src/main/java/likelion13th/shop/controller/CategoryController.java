package likelion13th.shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion13th.shop.DTO.response.ItemResponse;
import likelion13th.shop.global.api.ApiResponse;
import likelion13th.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "카테고리", description = "카테고리 관련 API 입니다.")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // GET /categories/{categoryId}/items - 카테고리별 상품 조회
    @Operation(summary = "카테고리별 상품 조회")
    @GetMapping("/{categoryName}/items")
    public ResponseEntity<List<ItemResponse>> getItemsByCategory(@PathVariable String categoryName) {
        List<ItemResponse> response = categoryService.findItemsByCategoryName(categoryName);
        return ResponseEntity.ok(response);
    }
}

// CategoryController.java
// 카테고리 이름으로 상품을 조회하는 API 엔드포인트를 정의 (id로 상품을 조회하는 것보다 직관적)
// Service한테 "해줘"하고 요청을 던져주는 코드
