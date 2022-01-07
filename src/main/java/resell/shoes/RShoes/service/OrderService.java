package resell.shoes.RShoes.service;

import com.github.pagehelper.Page;
import org.springframework.http.ResponseEntity;
import resell.shoes.RShoes.dto.CancelDTO;
import resell.shoes.RShoes.dto.OrderDTO;
import resell.shoes.RShoes.dto.OrderGetDTO;
import resell.shoes.RShoes.dto.PageShoesDTO;

public interface OrderService {

    ResponseEntity<?> orderShoes(OrderDTO order);

    ResponseEntity<?> cancel(CancelDTO cancel);

    Page<OrderGetDTO> getOrders(int page, String userId);

    Page<PageShoesDTO> getSellShoes(int page, String userId);
}
