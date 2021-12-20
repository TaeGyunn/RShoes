package resell.shoes.RShoes.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Order_shoes {

    private Long OrderNo;           // 주문번호
    private Shoes OrderShoes;        // 신발
    private Delivery OrderDelivery;  // 주소

}
