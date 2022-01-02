package resell.shoes.RShoes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import resell.shoes.RShoes.dto.Status;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Shoes {

    private Long shoesNo;       // 신발 번호
    private Brand brand;        // 브랜드
    private Category category;  // 카테고리
    private Inventory inventory;// 창고
    private User user;          // 판매유저
    private String shoesName;   // 이름
    private Integer price;      // 가격
    private String color;       // 색상
    private Integer size;        // 사이즈
    private Status status;  //제품상태
    private LocalDateTime createDateTime;

    public Shoes(Brand brand, Category category, User user, String shoesName, Integer price, String color, Integer size, LocalDateTime createDateTime){
        this.brand = brand;
        this.category = category;
        this.user = user;
        this.shoesName = shoesName;
        this.price = price;
        this.color = color;
        this.size = size;
        this.createDateTime = createDateTime;
    }

    public void changeShoesNo(Long shoesNo){this.shoesNo = shoesNo;}
    public void changeStatus(Status status){this.status = status;}

}
