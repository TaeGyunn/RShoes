package resell.shoes.RShoes.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resell.shoes.RShoes.dto.LoginDTO;
import resell.shoes.RShoes.dto.UserResponseDTO;
import resell.shoes.RShoes.repository.UserRepository;
import resell.shoes.RShoes.service.UserService;
import resell.shoes.RShoes.util.JwtTokenProvider;
import resell.shoes.RShoes.util.Response;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Response response;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;


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
