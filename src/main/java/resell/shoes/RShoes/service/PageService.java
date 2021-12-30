package resell.shoes.RShoes.service;

import com.github.pagehelper.Page;
import org.springframework.http.ResponseEntity;
import resell.shoes.RShoes.dto.PageShoesDTO;

public interface PageService {

    Page<PageShoesDTO> getAllShoes(int page);

    Page<PageShoesDTO> getBrandShoes(int page, String brandName);

    Page<PageShoesDTO> getCategoryShoes(int page, String categoryName);

    Page<PageShoesDTO> getPageShoesDetail(int page, String brandName, String categoryName);
}
