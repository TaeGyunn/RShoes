package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.entity.Pay;

@Repository
public interface PayRepository {

    Long insertPay(Pay pay);

    Pay findByPano(Long pano);
}
