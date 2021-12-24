package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.entity.Shoes;

@Repository
public interface ShoesRepository {

    Long insertShoes(Shoes shoes);
}
