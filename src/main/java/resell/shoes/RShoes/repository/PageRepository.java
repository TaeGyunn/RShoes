package resell.shoes.RShoes.repository;

import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.dto.PageShoesDTO;
import resell.shoes.RShoes.entity.Shoes;

@Repository
public interface PageRepository {
    
    // 상품전체페이징
    Page<PageShoesDTO> getAllShoesDTO(int page);
    
    // 상품브랜드페이징
    Page<PageShoesDTO> getBrandShoesDTO(int page, Long brandNo);
    
    // 상품카테고리페이징
    Page<PageShoesDTO> getCategoryShoesDTO(int page, Long categoryNo);
    
    // 상품조건페이징
    Page<PageShoesDTO> getShoesDetailDTO(int page, Long brandNo, Long categoryNo);
    
}
