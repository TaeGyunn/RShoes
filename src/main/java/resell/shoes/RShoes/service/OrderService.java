package resell.shoes.RShoes.service;

import org.springframework.http.ResponseEntity;
import resell.shoes.RShoes.dto.CancelDTO;
import resell.shoes.RShoes.dto.OrderDTO;

public interface OrderService {

    ResponseEntity<?> orderShoes(OrderDTO order);

    ResponseEntity<?> cancel(CancelDTO cancel);
}
