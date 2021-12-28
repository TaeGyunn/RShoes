package resell.shoes.RShoes.repository;

import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.dto.PageShoesDTO;
import resell.shoes.RShoes.entity.Shoes;

@Repository
public interface PageRepository {

    Page<PageShoesDTO> getAllShoesDTO(int page);
}
