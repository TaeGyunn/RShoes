package resell.shoes.RShoes.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Category {
    
    private Long categoryNo;       // 카테고리 번호
    private String categoryName;    // 카테고리 이름
}
