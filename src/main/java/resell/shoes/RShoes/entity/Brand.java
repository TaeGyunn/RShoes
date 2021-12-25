package resell.shoes.RShoes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Brand {
    
    private Long brandNo;       // 브랜드 번호
    private User user;          // 유저
    private String brandName;    // 브랜드 이름

    public Brand(User user, String brandName){
        this.user = user;
        this.brandName = brandName;
    }
}
