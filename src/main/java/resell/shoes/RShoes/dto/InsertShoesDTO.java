package resell.shoes.RShoes.dto;

import lombok.Data;
import resell.shoes.RShoes.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class InsertShoesDTO {

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "아이디를 입력해주세요")
    private String userId;

    @NotBlank(message = "브랜드를 입력해주세요")
    private String brand;
    
    @NotBlank(message = "카테고리를 입력해주세요")
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String category;
    
    @NotBlank(message = "가격을 입력해주세요")
    @Pattern(regexp = "^[0-9]*$")
    private Integer price;
    
    @NotBlank(message = "색상을 입력해주세요")
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String color;

    @NotBlank(message = "사이즈를 입력해주세요")
    @Pattern(regexp = "^[A-Z]*$")
    private String size;
}
