package resell.shoes.RShoes.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resell.shoes.RShoes.dto.CategoryDTO;
import resell.shoes.RShoes.repository.CategoryRepository;
import resell.shoes.RShoes.service.CategoryService;
import resell.shoes.RShoes.util.Response;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final Response response;

    @Override
    public ResponseEntity<?> insertCategory(CategoryDTO category) {

        Map<String, Boolean> map = new HashMap<>();

        if(categoryRepository.checkByName(category.getName())){
            map.put("insert", false);
            return response.fail(map, "카테고리가 이미 존재합니다", HttpStatus.BAD_REQUEST);
        }

        categoryRepository.insertCategory(category);
        map.put("insert", true);

        return response.success(map, "카테고리 생성 완료", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteCategory(CategoryDTO category) {

        Map<String, Boolean> map = new HashMap<>();

        if(!categoryRepository.checkByName(category.getName())){
            map.put("delete", false);
            return response.fail(map, "카테고리가 없습니다", HttpStatus.BAD_REQUEST);
        }

        categoryRepository.deleteCategory(category);

        map.put("delete", true);

        return response.success(map, "카테고리 삭제 완료", HttpStatus.OK);
    }
}
