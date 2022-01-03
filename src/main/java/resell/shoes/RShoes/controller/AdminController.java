package resell.shoes.RShoes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import resell.shoes.RShoes.dto.CategoryDTO;
import resell.shoes.RShoes.dto.BrandDTO;
import resell.shoes.RShoes.dto.InventoryDTO;
import resell.shoes.RShoes.repository.BrandRepository;
import resell.shoes.RShoes.service.BrandService;
import resell.shoes.RShoes.service.CategoryService;
import resell.shoes.RShoes.service.Helper;
import resell.shoes.RShoes.service.InventoryService;
import resell.shoes.RShoes.util.Response;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    private final Response response;
    private final CategoryService categoryService;
    private final InventoryService inventoryService;
    private final BrandService brandService;
    
    //브랜드생성
    @PostMapping("/insertBrand")
    public ResponseEntity<?> insertBrand(@Validated @RequestBody BrandDTO brand, Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return brandService.insertBrand(brand);

    }
    
    //카테고리 생성
    @PostMapping("/insertCategory")
    public ResponseEntity<?> addCategory(@Validated @RequestBody CategoryDTO category, Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return categoryService.insertCategory(category);

    }

    //주문상품 검수
    @PutMapping("/checkInventory")
    public ResponseEntity<?> checkInventory(@Validated @RequestBody InventoryDTO inventory, Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return inventoryService.checkInventory(inventory);

    }


    //브랜드 삭제
    @DeleteMapping("/deleteBrand")
    public ResponseEntity<?> deleteBrand(@Validated @RequestBody BrandDTO brand, Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return brandService.deleteBrand(brand);
    }
    
    //카테고리 삭제
    @DeleteMapping("/deleteCategory")
    public ResponseEntity<?> deleteCategory(@Validated @RequestBody CategoryDTO category, Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return categoryService.deleteCategory(category);

    }


}
