package resell.shoes.RShoes.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InventoryDTO {

    private Long shoesNo;       //신발No

    private String userId;      //adminId

    private Long deliveryNo;    //배달no

    private Boolean check;      //검수결과
}
