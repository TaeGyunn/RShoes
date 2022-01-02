package resell.shoes.RShoes.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resell.shoes.RShoes.dto.OrderDTO;
import resell.shoes.RShoes.dto.Status;
import resell.shoes.RShoes.entity.*;
import resell.shoes.RShoes.repository.*;
import resell.shoes.RShoes.service.OrderService;
import resell.shoes.RShoes.util.Response;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final DeliveryRepository deliveryRepository;
    private final PayRepository payRepository;
    private final UserRepository userRepository;
    private final ShoesRepository shoesRepository;
    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;
    private final Response response;

    @Override
    public ResponseEntity<?> orderShoes(OrderDTO order) {

        Map<String, Boolean> map = new HashMap<>();

        Delivery delivery = new Delivery(
                order.getAddress(),
                order.getZip_code(),
                Status.READY);

        Long dno = deliveryRepository.insertDelivery(delivery);
        delivery = deliveryRepository.findByDno(dno);

        Pay pay = new Pay(
                order.getPayName(),
                order.getPayType(),
                LocalDateTime.now(),
                order.getPayContent()
        );


        Long pano = payRepository.insertPay(pay);
        pay = payRepository.findByPano(pano);

        User buyUser = userRepository.findById(order.getUserId()).orElse(null);
        Shoes shoes = shoesRepository.findByShoesNo(order.getShoesNo());
        Order_shoes order_shoes = new Order_shoes(
                buyUser,
                shoes,
                delivery,
                pay,
                order.getPayment()
        );

        orderRepository.insertOrder(order_shoes);

        Inventory inventory = new Inventory(Status.Inspection);

        Long inventoryNo = inventoryRepository.insertInventory(inventory);

        shoesRepository.modifyIno(inventoryNo, order.getShoesNo());

        map.put("order", true);
        return response.success(map, "상품 주문이 완료되었습니다", HttpStatus.OK);
    }
}
