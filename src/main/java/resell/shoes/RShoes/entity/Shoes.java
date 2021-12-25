package resell.shoes.RShoes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shoes {

    private Long shoesNo;       // 신발 번호
    private Brand brand;        // 브랜드
    private Category category;  // 카테고리
    private String shoesName;   // 이름
    private Integer price;      // 가격
    private String color;       // 색상
    private String size;        // 사이즈

    public Shoes(Brand brand, Category category, String shoesName, Integer price, String color, String size){
        this.brand = brand;
        this.category = category;
        this.shoesName = shoesName;
        this.price = price;
        this.color = color;
        this.size = size;
    }

}
