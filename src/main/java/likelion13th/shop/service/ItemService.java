package likelion13th.shop.service;

import jakarta.transaction.Transactional;
import likelion13th.shop.DTO.request.ItemRequestDto;
import likelion13th.shop.DTO.response.ItemResponseDto;
import likelion13th.shop.domain.Item;
import likelion13th.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    // 상품 전체 조회
    /*
        itemRepository.findAll()로 모든 상품 데이터를 DB에서 가져옴.
        stream()으로 리스트를 스트림 형태로 바꾸고,
        map(ItemResponseDto::new)로 각 엔티티를 DTO로 변환하고
        .toList()로 다시 리스트로 바꿈.
     */
    public List<ItemResponseDto> findAll() {
        return itemRepository.findAll().stream()
                .map(ItemResponseDto::new)
                .toList();
    }

    // 상품 개별 조회
    // id로 객체 탐색 -> 찾으면 DTO로 변환해 반환 : 없으면 예외 발생
    public ItemResponseDto findById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품 없음"));
        return new ItemResponseDto(item);
    }

    // 상품 생성
    @Transactional
    public void create(ItemRequestDto request) {
        Item item = new Item(
                request.getItem_name(),
                request.getItem_price(),
                request.getItem_remain(),
                request.getItem_img(),
                request.getItem_detail()
        );
        itemRepository.save(item);
    }

    // 상품 수정
    @Transactional
    public void update(Long id, ItemRequestDto request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품 없음"));

        item.update(
                request.getItem_name(),
                request.getItem_price(),
                request.getItem_remain(),
                request.getItem_img(),
                request.getItem_detail()
        );
    }

    // 상품 삭제
    @Transactional
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}

/*
    Django에선 views.py 안에 섞여 있던 비즈니스 로직을 분리한 것
    진짜 핵심 로직 수행 (예: 주문 처리, 마일리지 계산 등)
    Controller는 Service한테 “이거 해줘~”만 말함
 */
