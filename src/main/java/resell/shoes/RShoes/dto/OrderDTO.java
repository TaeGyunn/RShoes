package resell.shoes.RShoes.dto;

import lombok.Data;

@Data
public class OrderDTO {

    private Long shoesNo;
    private String userId;
    private String payment;

    //주소
    private String address;
    private Integer zip_code;

    //결제정보
    private String payName;
    private String payType;
    private String payContent;
}
