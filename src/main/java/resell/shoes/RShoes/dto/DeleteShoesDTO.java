package resell.shoes.RShoes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class DeleteShoesDTO {
    
    @NotBlank(message = "shoesNo를 입력해주세요")
    private Long shoesNo;

    @NotBlank(message = "userId를 입력해주세요")
    private String userId;
}
