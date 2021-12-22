package resell.shoes.RShoes.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDTO {

    @NotBlank(message = "카테고리 이름을 입력해주세요")
    private String name;
}
