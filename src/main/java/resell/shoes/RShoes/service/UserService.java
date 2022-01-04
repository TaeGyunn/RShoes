package resell.shoes.RShoes.service;

import org.springframework.http.ResponseEntity;
import resell.shoes.RShoes.dto.FindPwDTO;
import resell.shoes.RShoes.dto.JoinDTO;
import resell.shoes.RShoes.dto.LoginDTO;
import resell.shoes.RShoes.dto.MailDTO;

public interface UserService {

    ResponseEntity<?> login(LoginDTO login);

    ResponseEntity<?> join(JoinDTO join);

    ResponseEntity<?> checkById(String id);

    ResponseEntity<?> checkByEmail(String email);

    ResponseEntity<?> checkByPhone(String phone);

    ResponseEntity<?> findId(String email, String name);

    ResponseEntity<?> checkByEmailAndId(FindPwDTO findPwDTO);
}
