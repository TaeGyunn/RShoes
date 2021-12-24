package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.entity.Brand;

@Repository
public interface BrandRepository {
    
    // 브랜드 이름 중복체크
    Boolean checkByName(String brandName);
    
    // 브랜드 기입
    Integer insertBrand(Brand brand);
    
    // 브랜드 찾기
    Brand findByName(String brandName);
}
