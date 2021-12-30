package resell.shoes.RShoes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import resell.shoes.RShoes.dto.FindLoginDTO;
import resell.shoes.RShoes.dto.JoinDTO;
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
    
    //아이디 중복 체크
    @GetMapping("/checkById/{id}")
    public ResponseEntity<?> checkById(@PathVariable(name = "id") String id){

        return userService.checkById(id);
    }
    
    //이메일 중복 체크
    @GetMapping("/checkByEmail/{email}")
    public ResponseEntity<?> checkByEmail(@PathVariable(name = "email") String email){

        return userService.checkByEmail(email);
    }
    
    //핸드폰 중복 체크
    @GetMapping("/checkByPhone/{phone}")
    public ResponseEntity<?> checkByPhone(@PathVariable(name = "phone") String phone){

        return userService.checkByPhone(phone);
    }
    
    //회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@Validated @RequestBody JoinDTO join, Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return userService.join(join);
    }
    
    //로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginDTO login, Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return userService.login(login);
    }
    
    //아이디찾기
    @PostMapping("/findId")
    public ResponseEntity<?> findId(@Validated @RequestBody FindLoginDTO.FindIdDTO findId){

       return userService.findId(findId.getEmail(), findId.getUserName());
    }

}
