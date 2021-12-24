package resell.shoes.RShoes.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import resell.shoes.RShoes.dto.InsertShoesDTO;

import java.util.List;

public interface ShoesService {

    ResponseEntity<?> insertShoes(InsertShoesDTO insertShoesDTO, List<MultipartFile> files);


}
