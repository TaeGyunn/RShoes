package resell.shoes.RShoes.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import resell.shoes.RShoes.dto.CategoryDTO;
import resell.shoes.RShoes.repository.CategoryRepository;
import resell.shoes.RShoes.service.AdminService;
import resell.shoes.RShoes.util.Response;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final CategoryRepository categoryRepository;
    private final Response response;

    @Override
    public ResponseEntity<?> insertCategory(CategoryDTO category) {

        Map<String, String> map = new HashMap<>();

        if(categoryRepository.checkByName(category.getName())) {
            map.put("duplicate", "true");
            return response.fail(map, "카테고리가 존재합니다", HttpStatus.OK);
        }

        if(categoryRepository.insertCategory(category) == 1){
            map.put("insert", "success");
            return response.success(map, "카테고리 생성 성공", HttpStatus.OK);
        }

        return null;
    }

    @Override
    public ResponseEntity<?> deleteCategory(CategoryDTO category) {

        Map<String, String> map = new HashMap<>();

        if(categoryRepository.checkByName(category.getName())){

            categoryRepository.deleteCategory(category);
            map.put("delete", "success");
            return response.success(map, "카테고리 삭제 성공", HttpStatus.OK);
        }

        return null;
    }
}