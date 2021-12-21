package resell.shoes.RShoes.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resell.shoes.RShoes.dto.JoinDTO;
import resell.shoes.RShoes.dto.LoginDTO;
import resell.shoes.RShoes.dto.UserResponseDTO;
import resell.shoes.RShoes.repository.UserRepository;
import resell.shoes.RShoes.service.UserService;
import resell.shoes.RShoes.util.JwtTokenProvider;
import resell.shoes.RShoes.util.Response;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Response response;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> checkById(String id) {

        Map<String, String> map = new HashMap<>();

        if(userRepository.checkById(id)){
            map.put("duplicate","true");
            return response.success(map, "아이디가 중복입니다", HttpStatus.OK);
        }

        map.put("duplicate", "false");
        return response.success(map, "사용가능한 아이디 입니다", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> checkByEmail(String email) {

        Map<String, String> map = new HashMap<>();

        if(userRepository.checkByEmail(email)){
            map.put("duplicate","true");
            return response.success(map, "이메일이 중복입니다", HttpStatus.OK);
        }

        map.put("duplicate", "false");
        return response.success(map, "사용가능한 이메일 입니다", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> checkByPhone(String phone) {

        Map<String, String> map = new HashMap<>();

        if(userRepository.checkByPhone(phone)){
            map.put("duplicate","true");
            return response.success(map, "번호가 중복입니다", HttpStatus.OK);
        }

        map.put("duplicate", "false");
        return response.success(map, "사용가능한 번호 입니다", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> join(JoinDTO join) {

        String encodePassword = passwordEncoder.encode(join.getPw());
        join.setPw(encodePassword);

        int check = userRepository.join(join);
        if(check == 0){
            response.fail("회원가입에 실패하였습니다", HttpStatus.BAD_REQUEST);
        }
        return response.success("회원가입에 성공하였습니다.");

    }

    @Override
    public ResponseEntity<?> login(LoginDTO login) {

        if(userRepository.findById(login.getId()).orElse(null) == null){
            return response.fail("유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        UsernamePasswordAuthenticationToken authenticationToken = login.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        UserResponseDTO.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return response.success(tokenInfo, "로그인 성공하였습니다.", HttpStatus.OK);

    }

}
