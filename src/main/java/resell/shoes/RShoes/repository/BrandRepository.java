package resell.shoes.RShoes.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.dto.BrandDTO;
import resell.shoes.RShoes.entity.Brand;

@Repository
public interface BrandRepository {
    
    // 브랜드 이름 중복체크
    Boolean checkByName(String brandName);
    
    // 브랜드 기입
    Integer insertBrand(BrandDTO brand);

    // 브랜드 삭제
    void deleteBrand(BrandDTO brand);
    
    // 브랜드 찾기
    Brand findByName(String brandName);
    
}
