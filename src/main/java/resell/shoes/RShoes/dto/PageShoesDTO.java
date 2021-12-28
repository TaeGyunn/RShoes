package resell.shoes.RShoes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import resell.shoes.RShoes.entity.Shoes;

import java.util.List;

@Data
@NoArgsConstructor
public class PageShoesDTO {

    private Long shoesNo;
    private Long brandNo;
    private Long categoryNo;
    private String shoesName;
    private String brandName;
    private String categoryName;
    private Integer price;
    private String color;
    private Integer size;
    private List<String> url;

    public PageShoesDTO(Shoes shoes){
        this.shoesNo = shoes.getShoesNo();
        this.brandNo = shoes.getBrand().getBrandNo();
        this.categoryNo = shoes.getCategory().getCategoryNo();
        this.shoesName = shoes.getShoesName();
        this.brandName = shoes.getBrand().getBrandName();
        this.categoryName = shoes.getCategory().getCategoryName();
        this.price = shoes.getPrice();
        this.color = shoes.getColor();
        this.size = shoes.getSize();
        
    }



}
