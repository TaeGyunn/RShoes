package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.dto.CategoryDTO;
import resell.shoes.RShoes.entity.Category;

@Repository
public interface CategoryRepository {
    
    // 카테고리이름 확인
    Boolean checkByName(String categoryName);
    
    // 카테고리 아이디로 카테고리 찾기
    Category findByName(String categoryName);
    
    // 카테고리 생성
    Integer insertCategory(CategoryDTO categoryDTO);
    
    // 카테고리 제거
    Integer deleteCategory(CategoryDTO category);
}
