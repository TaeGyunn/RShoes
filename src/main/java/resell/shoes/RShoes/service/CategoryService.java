package resell.shoes.RShoes.service;

import org.springframework.http.ResponseEntity;
import resell.shoes.RShoes.dto.CategoryDTO;

public interface CategoryService {

    ResponseEntity<?> insertCategory(CategoryDTO category);

    ResponseEntity<?> deleteCategory(CategoryDTO category);
}
