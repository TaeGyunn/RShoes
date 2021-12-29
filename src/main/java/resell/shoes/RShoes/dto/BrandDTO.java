package resell.shoes.RShoes.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class BrandDTO {

    @NotBlank(message = "브랜드명을 입력해주세요")
    private String brandName;


}
