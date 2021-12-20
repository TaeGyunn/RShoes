package resell.shoes.RShoes.service;

import org.springframework.http.ResponseEntity;
import resell.shoes.RShoes.dto.LoginDTO;

public interface UserService {

    ResponseEntity<?> login(LoginDTO login);
}
