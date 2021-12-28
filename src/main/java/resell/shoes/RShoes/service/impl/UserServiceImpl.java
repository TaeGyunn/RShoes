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
import resell.shoes.RShoes.dto.InsertBrandDTO;
import resell.shoes.RShoes.dto.JoinDTO;
import resell.shoes.RShoes.dto.LoginDTO;
import resell.shoes.RShoes.dto.UserResponseDTO;
import resell.shoes.RShoes.entity.Brand;
import resell.shoes.RShoes.entity.User;
import resell.shoes.RShoes.repository.BrandRepository;
import resell.shoes.RShoes.repository.UserRepository;
import resell.shoes.RShoes.service.UserService;
import resell.shoes.RShoes.util.JwtTokenProvider;
import resell.shoes.RShoes.util.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BrandRepository brandRepository;
    private final Response response;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> checkById(String id) {

        Map<String, Boolean> map = new HashMap<>();

        if(userRepository.checkById(id)){
            map.put("duplicate",true);
            return response.fail(map, "아이디가 중복입니다", HttpStatus.OK);
        }

        map.put("duplicate", true);
        return response.success(map, "사용가능한 아이디 입니다", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> checkByEmail(String email) {

        Map<String, Boolean> map = new HashMap<>();

        if(userRepository.checkByEmail(email)){
            map.put("duplicate",true);
            return response.fail(map, "이메일이 중복입니다", HttpStatus.OK);
        }

        map.put("duplicate", false);
        return response.success(map, "사용가능한 이메일 입니다", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> checkByPhone(String phone) {

        Map<String, Boolean> map = new HashMap<>();

        if(userRepository.checkByPhone(phone)){
            map.put("duplicate",true);
            return response.fail(map, "번호가 중복입니다", HttpStatus.OK);
        }

        map.put("duplicate", false);
        return response.success(map, "사용가능한 번호 입니다", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> insertBrand(InsertBrandDTO brand) {

        Map<String, Boolean> map = new HashMap<>();
        if(brandRepository.checkByName(brand.getBrandName())){
            map.put("insert", false);
            return response.fail(map, "브랜드이름이 중복입니다.", HttpStatus.OK);
        }

        User user = userRepository.findById(brand.getUserId()).orElse(null);
        if(user == null){
            map.put("id", null);
            return response.fail(map,"유저가 없습니다.", HttpStatus.OK);
        }

        Brand newBrand = new Brand(user, brand.getBrandName());

        brandRepository.insertBrand(newBrand);

        map.put("insert", true);
        return response.success(map, "브랜드 생성이 성공했습니다.",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findId(String email, String name) {

        Map<String, Boolean> map = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();

        User user = userRepository.findByEmailAndName(email, name).orElse(null);
        if(user == null){
            map.put("find", false);
            return response.fail(map, "이메일과 이름에 일치하는 아이디가 없습니다", HttpStatus.OK);
        }

        map.put("find", true);
        map2.put("id", user.getId());
        List<Map> mapList = new ArrayList<>();
        mapList.add(map);
        mapList.add(map2);
        return response.success(mapList, "아이디 찾기 성공", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> join(JoinDTO join) {

        Map<String, Boolean> map = new HashMap<>();

        String encodePassword = passwordEncoder.encode(join.getPw());
        join.setPw(encodePassword);

        int check = userRepository.join(join);
        if(check == 0){
            map.put("join", false);
            response.fail(map,"회원가입에 실패하였습니다", HttpStatus.BAD_REQUEST);
        }
        map.put("join", true);
        return response.success(map,"회원가입에 성공하였습니다.",HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> login(LoginDTO login) {

        if(userRepository.findById(login.getUserId()).orElse(null) == null){
            return response.fail("유저가 존재하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        UsernamePasswordAuthenticationToken authenticationToken = login.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        UserResponseDTO.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return response.success(tokenInfo, "로그인 성공하였습니다.", HttpStatus.OK);

    }

}
