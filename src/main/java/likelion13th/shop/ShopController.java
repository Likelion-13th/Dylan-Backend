package likelion13th.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    // ✅ 전체 상품 조회
    @GetMapping
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    // ✅ 상품 등록
    @PostMapping
    public Shop createShop(@RequestBody Shop shop) {
        return shopRepository.save(shop);
    }

    // ✅ 단일 상품 조회
    @GetMapping("/{id}")
    public Shop getShopById(@PathVariable Long id) {
        return shopRepository.findById(id).orElse(null);
    }

    // ✅ 상품 수정
    @PutMapping("/{id}")
    public Shop updateShop(@PathVariable Long id, @RequestBody Shop updated) {
        return shopRepository.findById(id).map(shop -> {
            shop.setName(updated.getName());
            shop.setMaker(updated.getMaker());
            shop.setContent(updated.getContent());
            shop.setPrice(updated.getPrice());
            shop.setImageUrl(updated.getImageUrl());
            return shopRepository.save(shop);
        }).orElse(null);
    }

    // ✅ 상품 삭제
    @DeleteMapping("/{id}")
    public void deleteShop(@PathVariable Long id) {
        shopRepository.deleteById(id);
    }
}
