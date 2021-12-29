package resell.shoes.RShoes.service;


import org.springframework.http.ResponseEntity;
import resell.shoes.RShoes.dto.BrandDTO;

public interface BrandService {

    ResponseEntity<?> insertBrand(BrandDTO brandDTO);

    ResponseEntity<?> deleteBrand(BrandDTO brandDTO);

}
