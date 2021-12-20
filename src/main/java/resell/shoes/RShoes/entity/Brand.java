package resell.shoes.RShoes.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Brand {
    
    private Long brandNo;       // 브랜드 번호
    private String brandName;    // 브랜드 이름
}
