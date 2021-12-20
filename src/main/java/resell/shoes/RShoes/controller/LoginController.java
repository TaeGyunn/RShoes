package resell.shoes.RShoes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import resell.shoes.RShoes.dto.LoginDTO;
import resell.shoes.RShoes.service.Helper;
import resell.shoes.RShoes.service.UserService;
import resell.shoes.RShoes.util.Response;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final Response response;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login, Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return userService.login(login);
    }



}
