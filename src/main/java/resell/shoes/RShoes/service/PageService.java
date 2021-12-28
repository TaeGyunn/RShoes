package resell.shoes.RShoes.service;

import org.springframework.http.ResponseEntity;

public interface PageService {

    ResponseEntity<?> getAllShoes(int page);
}
