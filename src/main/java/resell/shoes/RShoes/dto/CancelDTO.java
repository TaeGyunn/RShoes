package resell.shoes.RShoes.dto;

import lombok.Data;

@Data
public class CancelDTO {

    private String userId; //주문자 ID

    private Long orderNo;   //주문 No

}
