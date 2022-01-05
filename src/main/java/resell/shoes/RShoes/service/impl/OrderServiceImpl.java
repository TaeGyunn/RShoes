package resell.shoes.RShoes.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resell.shoes.RShoes.dto.CancelDTO;
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

        Pay pay = new Pay(
                order.getPayName(),
                order.getPayType(),
                LocalDateTime.now(),
                order.getPayContent()
        );


        Long pno = payRepository.insertPay(pay);


        User buyUser = userRepository.findById(order.getUserId()).orElse(null);
        Shoes shoes = shoesRepository.findByShoesNo(order.getShoesNo());

        Order_shoes order_shoes = new Order_shoes(
                buyUser,
                shoes,
                delivery,
                pay,
                order.getPayment(),
                Status.TRADE
        );


        orderRepository.insertOrder(order_shoes);

        Inventory inventory = new Inventory(Status.Inspection);

        Long inventoryNo = inventoryRepository.insertInventory(inventory);

        shoesRepository.modifyIno(inventory, order.getShoesNo(), Status.SOLDOUT);

        map.put("order", true);
        return response.success(map, "상품 주문이 완료되었습니다", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> cancel(CancelDTO cancel) {

        Map<String, Boolean> map = new HashMap<>();

        Order_shoes order = orderRepository.findByOrder(cancel.getOrderNo());

        Delivery delivery = deliveryRepository.findByDno(order.getOrderDelivery().getDeliveryNo());
        if(!delivery.getStatus().equals("READY")){
            map.put("cancel", false);
            return response.fail(map, "배송이 이미 진행되어 취소가 불가능합니다", HttpStatus.BAD_REQUEST);
        }
        if(order.getStatus() != Status.TRADE){
            map.put("cancel", false);
            return response.fail(map, "환불이 불가능 합니다", HttpStatus.BAD_REQUEST);
        }
        Shoes shoes = shoesRepository.findByShoesNo(order.getOrderShoes().getShoesNo());
        inventoryRepository.modifyStatus2(shoes.getInventory().getInventoryNo(), Status.FAIL.getValue());
        shoesRepository.modifyStatus(Status.TRADE.getValue(), order.getOrderShoes().getShoesNo());
        orderRepository.modifyStatus(Status.REFUND.getValue(), cancel.getOrderNo());
        deliveryRepository.modifyStatus(order.getOrderDelivery().getDeliveryNo(), Status.RETURN.getValue());

        map.put("cancel", true);

        return response.success(map, "환불이 완료되었습니다.", HttpStatus.OK);
    }
}
