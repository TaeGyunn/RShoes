package resell.shoes.RShoes.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import resell.shoes.RShoes.dto.OrderGetDTO;
import resell.shoes.RShoes.dto.PageShoesDTO;
import resell.shoes.RShoes.service.OrderService;
import resell.shoes.RShoes.service.UserService;
import resell.shoes.RShoes.util.Response;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final OrderService orderService;
    private final Response response;

    //구매내역
    @GetMapping("/user/getOrders/{page}/{userId}")
    public ResponseEntity<?> getOrders(@PathVariable(name = "userId") String userId,
                                       @PathVariable(name = "page") int page){

        Page<OrderGetDTO> p = orderService.getOrders(page, userId);

        return response.success(p, "구매내역 페이지", HttpStatus.OK);

    }

    //현재판매목록
   @GetMapping("/user/getSellShoes/{page}/{userId}")
    public ResponseEntity<?> getSellShoes(@PathVariable(name = "page") int page,
                                          @PathVariable(name="userId") String userId){

        Page<PageShoesDTO> getShoes = orderService.getSellShoes(page, userId);

        return null;
   }
   
   //TODO: 과거 판매내역

}