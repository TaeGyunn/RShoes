package resell.shoes.RShoes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import resell.shoes.RShoes.dto.CategoryDTO;
import resell.shoes.RShoes.service.AdminService;
import resell.shoes.RShoes.service.Helper;
import resell.shoes.RShoes.util.Response;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdminService adminService;
    private final Response response;

    @PostMapping(value = "/insertCategory")
    public ResponseEntity<?> addCategory(@Validated @RequestBody CategoryDTO category, Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return adminService.insertCategory(category);
    }

    @DeleteMapping(value = "/deleteCategory")
    public ResponseEntity<?> deleteCategory(@Validated @RequestBody CategoryDTO category, Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return adminService.deleteCategory(category);

    }


}
