package resell.shoes.RShoes.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resell.shoes.RShoes.dto.InventoryDTO;
import resell.shoes.RShoes.dto.Status;
import resell.shoes.RShoes.entity.Inventory;
import resell.shoes.RShoes.entity.Order_shoes;
import resell.shoes.RShoes.entity.Shoes;
import resell.shoes.RShoes.entity.User;
import resell.shoes.RShoes.repository.*;
import resell.shoes.RShoes.service.InventoryService;
import resell.shoes.RShoes.util.Response;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class InventoryImpl implements InventoryService {

    private final Response response;
    private final InventoryRepository inventoryRepository;
    private final ShoesRepository shoesRepository;
    private final UserRepository userRepository;
    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;

    @Override
    public ResponseEntity<?> checkInventory(InventoryDTO inventory) {

        Map<String, Boolean> map = new HashMap<>();
        Shoes shoes = shoesRepository.findByShoesNo(inventory.getShoesNo());
        if(shoes == null){
            map.put("inspection", false);
            return response.fail(map, "상품이 없습니다", HttpStatus.BAD_REQUEST);
        }
        Long inventoryNo = shoes.getInventory().getInventoryNo();
        User user = userRepository.findById(inventory.getUserId()).orElse(null);
        if(user == null){
            map.put("inspection", false);
            return response.fail(map, "관리자 아이디를 다시 확인해주세요", HttpStatus.BAD_REQUEST);
        }

        if(inventory.getCheck()){
            Inventory saveInventory = new Inventory(inventoryNo, Status.PASS, user.getUsername(), LocalDateTime.now());
            inventoryRepository.modifyStatus(saveInventory);
            Order_shoes order = orderRepository.findByShoes(shoes.getShoesNo());
            deliveryRepository.modifyStatus(order.getOrderDelivery().getDeliveryNo(), Status.ARRIVE.getValue());
            map.put("inspection", true);
            return response.success(map, "검수 통과", HttpStatus.OK);

        }else{
            Inventory saveInventory = new Inventory(inventoryNo, Status.FAIL, user.getUsername(),LocalDateTime.now());
            inventoryRepository.modifyStatus(saveInventory);
            Order_shoes order = orderRepository.findByShoes(shoes.getShoesNo());
            deliveryRepository.modifyStatus(order.getOrderDelivery().getDeliveryNo(), Status.RETURN.getValue());
            map.put("inspection", false);
            return response.success(map, "검수 실패", HttpStatus.OK);
        }
    }
}
