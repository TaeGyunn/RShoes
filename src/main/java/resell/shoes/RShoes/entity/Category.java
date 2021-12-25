package resell.shoes.RShoes.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class Category {
    
    private Long categoryNo;       // 카테고리 번호
    private String categoryName;    // 카테고리 이름
}
