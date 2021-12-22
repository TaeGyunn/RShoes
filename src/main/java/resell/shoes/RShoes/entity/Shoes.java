package resell.shoes.RShoes.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Shoes {

    private Long shoesNo;           // 신발 번호
    private Brand brand;        // 브랜드
    private Category category;  // 카테고리
    private String shoesName;        // 이름
    private Integer price;      // 가격
    private String color;       // 색상
    private String size;        // 사이즈

}
