package resell.shoes.RShoes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import resell.shoes.RShoes.dto.CancelDTO;
import resell.shoes.RShoes.dto.OrderDTO;
import resell.shoes.RShoes.service.Helper;
import resell.shoes.RShoes.service.OrderService;
import resell.shoes.RShoes.util.Response;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final Response response;
    private final OrderService orderService;
    
    // 주문하기
    @PostMapping("/user/order")
    public ResponseEntity<?> orderShoes(@Validated @RequestBody OrderDTO order,
                                        Errors errors){

        if(errors.hasErrors()){
            return response.invalidFields(Helper.refineErrors(errors));
        }

        return orderService.orderShoes(order);
    }

    // 주문취소
    @PostMapping("/user/cancel")
    public ResponseEntity<?> cancelOrder(@RequestBody CancelDTO cancel){

        return orderService.cancel(cancel);
    }

}
