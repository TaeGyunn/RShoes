package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.entity.Order_shoes;

@Repository
public interface OrderRepository {


    void insertOrder(Order_shoes order_shoes);
}
