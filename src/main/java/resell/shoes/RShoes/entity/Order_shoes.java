package resell.shoes.RShoes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import resell.shoes.RShoes.dto.Status;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class Order_shoes {

    private Long orderNo;           // 주문번호
    private User buyUser;            // 구매유저
    private Shoes orderShoes;        // 신발
    private Delivery orderDelivery;  // 주소
    private Pay orderPay;            // 결제정보
    private String payment;         // 결제수단
    private LocalDateTime orderDate; //주문시간
    private Status status;

    public Order_shoes(User buyUser, Shoes orderShoes, Delivery orderDelivery, Pay orderPay, String payment, Status status) {
        this.buyUser = buyUser;
        this.orderShoes = orderShoes;
        this.orderDelivery = orderDelivery;
        this.orderPay = orderPay;
        this.payment = payment;
        this.status = status;
    }

}
