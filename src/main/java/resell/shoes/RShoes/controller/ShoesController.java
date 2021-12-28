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

    private final UserService userService;
    private final ShoesService shoesService;
    private final Response response;
    private final PageService pageService;


    @GetMapping("/pageShoes/{page}")
    public ResponseEntity<?> getPageShoes(@PathVariable(name = "page") int page){

        PageInfo<PageShoesDTO> p = new PageInfo<>(pageService.getAllShoes(page));

        return response.success(p,"신발 전체 페이징", HttpStatus.OK);
    }

    @PostMapping("/user/insertShoes")
    public ResponseEntity<?> insertShoes(@Validated @RequestPart InsertShoesDTO shoes,
                                         @RequestPart List<MultipartFile> files){


        return shoesService.insertShoes(shoes, files);
    }

    @PostMapping("/user/insertBrand")
    public ResponseEntity<?> insertBrand(@Validated @RequestBody InsertBrandDTO brand, Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return userService.insertBrand(brand);
    }

    @PutMapping("/user/modifyShoes")
    public ResponseEntity<?> modifyShoes(@Validated @RequestPart ModifyShoesDTO shoes,
                                        @RequestPart(required = false) List<MultipartFile> files){

        return shoesService.modifyShoes(shoes, files);
    }

    @DeleteMapping("/user/deleteShoes")
    public ResponseEntity<?> deleteShoes(@Validated @RequestBody DeleteShoesDTO shoes,
                                         Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return shoesService.deleteShoes(shoes);
    }






}
