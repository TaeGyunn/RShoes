package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.dto.Status;
import resell.shoes.RShoes.entity.Order_shoes;

@Repository
public interface OrderRepository {

    void insertOrder(Order_shoes order_shoes);

    Order_shoes findByShoes(Long shoesNo);

    Order_shoes findByOrder(Long orderNo);

    void modifyStatus(String refund, Long orderNo);
}
