package resell.shoes.RShoes.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import resell.shoes.RShoes.dto.Status;

@Getter
@RequiredArgsConstructor
public class Delivery {

private Long deliveryNo;         // 배달번호
    private String address;     // 주소
    private Integer zip_code;   // 우편번호
    private Status status;      // 배달상태
}
