package resell.shoes.RShoes.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import resell.shoes.RShoes.dto.*;
import resell.shoes.RShoes.service.Helper;
import resell.shoes.RShoes.service.PageService;
import resell.shoes.RShoes.service.ShoesService;
import resell.shoes.RShoes.service.UserService;
import resell.shoes.RShoes.util.Response;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ShoesController {

    private final ShoesService shoesService;
    private final Response response;
    private final PageService pageService;

    
    // 상품 전체 페이징
    @GetMapping("/pageShoes/{page}")
    public ResponseEntity<?> getPageShoes(@PathVariable(name = "page") int page){

        PageInfo<PageShoesDTO> p = new PageInfo<>(pageService.getAllShoes(page));

        return response.success(p,"신발 전체 페이징", HttpStatus.OK);
    }
    
    // 상품 브랜드전체 페이징
    @GetMapping("/pageBrandShoes/{page}/{brand}")
    public ResponseEntity<?> getPageBrandShoes(@PathVariable(name = "page") int page,
                                               @PathVariable(name = "brand") String brandName){

        PageInfo<PageShoesDTO> p = new PageInfo<>(pageService.getBrandShoes(page, brandName));

        return response.success(p, "상품 브랜드 전체 페이징", HttpStatus.OK);


    }

    // 상품 카테고리별 페이징
    @GetMapping("/pageBrandShoes/{page}/{category}")
    public ResponseEntity<?> getPageCategoryShoes(@PathVariable(name = "page") int page,
                                                  @PathVariable(name = "category") String categoryName){

        PageInfo<PageShoesDTO> p = new PageInfo<>(pageService.getCategoryShoes(page, categoryName));

        return response.success(p, "상품 카테고리별 페이징", HttpStatus.OK);
    }
    
    // 상품 브랜드 && 카테고리 페이징
    @GetMapping("/pageShoes/{page}/{brand}/{category}")
    public ResponseEntity<?> getPageShoesDetail(@PathVariable(name = "page") int page,
                                                @PathVariable(name = "brand") String brandName,
                                                @PathVariable(name = "category") String categoryName){

        PageInfo<PageShoesDTO> p = new PageInfo<>(pageService.getPageShoesDetail(page, brandName, categoryName));

        return response.success(p, "상품 조건 페이징", HttpStatus.OK);
    }

    // 상품추가
    @PostMapping("/user/insertShoes")
    public ResponseEntity<?> insertShoes(@Validated @RequestPart InsertShoesDTO shoes,
                                         @RequestPart List<MultipartFile> files){


        return shoesService.insertShoes(shoes, files);
    }
    // 상품수정
    @PutMapping("/user/modifyShoes")
    public ResponseEntity<?> modifyShoes(@Validated @RequestPart ModifyShoesDTO shoes,
                                         @RequestPart(required = false) List<MultipartFile> files){

        return shoesService.modifyShoes(shoes, files);
    }
    // 상품삭제
    @DeleteMapping("/user/deleteShoes")
    public ResponseEntity<?> deleteShoes(@Validated @RequestBody DeleteShoesDTO shoes,
                                         Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return shoesService.deleteShoes(shoes);
    }

}
