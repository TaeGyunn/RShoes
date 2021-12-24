package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.dto.CategoryDTO;
import resell.shoes.RShoes.entity.Category;

@Repository
public interface CategoryRepository {

    Boolean checkByName(String categoryName);

    Category findByName(String categoryName);

    Integer insertCategory(CategoryDTO categoryDTO);

    Integer deleteCategory(CategoryDTO category);
}
