package resell.shoes.RShoes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import resell.shoes.RShoes.dto.InsertBrandDTO;
import resell.shoes.RShoes.dto.InsertShoesDTO;
import resell.shoes.RShoes.dto.ModifyShoesDTO;
import resell.shoes.RShoes.service.Helper;
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


    @PostMapping("/user/insertShoes")
    public ResponseEntity<?> insertShoes(@Validated@RequestPart InsertShoesDTO shoes,
                                         @RequestPart List<MultipartFile> files,
                                         Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return shoesService.insertShoes(shoes, files);
    }

    @PostMapping("/user/insertBrand")
    public ResponseEntity<?> insertBrand(@Validated@RequestBody InsertBrandDTO brand, Errors errors){

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






}
