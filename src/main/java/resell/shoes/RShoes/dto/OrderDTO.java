package resell.shoes.RShoes.dto;

import lombok.Data;

@Data
public class OrderDTO {

    private Long shoesNo;       //신발 no
    private String userId;      //구매자 no
    private String payment;     //결제수단

    //주소
    private String address;
    private Integer zip_code;

    //결제정보
    private String payName;
    private String payType;
    private String payContent;
}
