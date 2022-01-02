package resell.shoes.RShoes.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class InventoryDTO {

    @NotBlank
    private Long shoesNo;

    @NotBlank
    private String userId;

    @NotBlank
    private Long deliveryNo;

    @NotBlank
    private Boolean check;
}
