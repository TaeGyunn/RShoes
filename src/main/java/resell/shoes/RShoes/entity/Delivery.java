package resell.shoes.RShoes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import resell.shoes.RShoes.dto.Status;

@Getter
@NoArgsConstructor
public class Delivery {

    private Long deliveryNo;    // 배달번호
    private String address;     // 주소
    private Integer zip_code;   // 우편번호
    private Status status;      // 배달상태

    public Delivery(String address, Integer zip_code, Status status){
        this.address = address;
        this.zip_code = zip_code;
        this.status = status;
    }

    public void changeStatus(Status status){
        this.status = status;
    }
    public void setDeliveryNo(Long deliveryNo){
        this.deliveryNo = deliveryNo;
    }
}
