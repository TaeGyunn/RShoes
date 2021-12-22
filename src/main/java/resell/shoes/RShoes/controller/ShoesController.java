package resell.shoes.RShoes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import resell.shoes.RShoes.dto.InsertBrandDTO;
import resell.shoes.RShoes.dto.InsertShoesDTO;
import resell.shoes.RShoes.service.Helper;
import resell.shoes.RShoes.service.UserService;
import resell.shoes.RShoes.util.Response;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ShoesController {

    private final UserService userService;
    private final Response response;


    @PostMapping("/user/insertShoes")
    public ResponseEntity<?> insertShoes(@RequestPart InsertShoesDTO shoes,
                                         @RequestPart List<MultipartFile> files){

        return null;
    }

    @PostMapping("/user/insertBrand")
    public ResponseEntity<?> insertBrand(@RequestBody InsertBrandDTO brand, Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return userService.insertBrand(brand);
    }
}
