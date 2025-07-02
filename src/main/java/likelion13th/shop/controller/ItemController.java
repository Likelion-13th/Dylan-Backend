package likelion13th.shop.controller;

import io.swagger.v3.oas.annotations.Operation;
import likelion13th.shop.DTO.request.ItemRequestDto;
import likelion13th.shop.DTO.response.ItemResponseDto;
import likelion13th.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    // GET /items - 전체 상품 조회
    @Operation(summary = "전체 상품 조회")
    @GetMapping
    public List<ItemResponseDto> getAllItems() {
        return itemService.findAll();
    }

    // POST /items - 상품 생성
    @Operation(summary = "상품 생성")
    @PostMapping
    public void createItem(@RequestBody ItemRequestDto request) {
        itemService.create(request);
    }

    // GET /items/{id} - 개별 상품 조회
    @Operation(summary = "개별 상품 조회")
    @GetMapping("/{id}")
    public ItemResponseDto getItem(@PathVariable Long id) {
        return itemService.findById(id);
    }

    // PUT /items/{id} - 상품 수정
    @Operation(summary = "상품 수정")
    @PutMapping("/{id}")
    public void updateItem(@PathVariable Long id, @RequestBody ItemRequestDto request) {
        itemService.update(id, request);
    }

    // DELETE /items/{id} - 상품 삭제
    @Operation(summary = "상품 삭제")
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.delete(id);
    }
}

// Service한테 "해줘"하고 요청을 던져주는 코드