package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.dto.Status;
import resell.shoes.RShoes.entity.Inventory;
import resell.shoes.RShoes.entity.Shoes;

import java.util.List;

@Repository
public interface ShoesRepository {
    
    // 상품생성
    Long insertShoes(Shoes shoes);
    
    // 상품수정
    Long modifyShoes(Shoes shoes);
    
    // 상품번호로 상품찾기
    Shoes findByShoesNo(Long shoesNo);
    
    // 상품삭제
    void deleteShoes(Long shoesNo);
    
    // 상품 ino, status 변경
    void modifyIno(Inventory inventory, Long shoesNo, Status soldout);
    
    // 상품 상태 변경
    void modifyStatus(String trade, Long shoesNo);
}
