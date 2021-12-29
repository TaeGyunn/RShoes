package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
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

}
