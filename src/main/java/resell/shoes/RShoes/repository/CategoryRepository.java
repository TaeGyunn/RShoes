package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.dto.CategoryDTO;

@Repository
public interface CategoryRepository {

    Boolean checkByName(String categoryName);

    Integer insertCategory(CategoryDTO categoryDTO);

    Integer deleteCategory(CategoryDTO category);
}
