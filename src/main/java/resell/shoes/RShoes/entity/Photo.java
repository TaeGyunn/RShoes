package resell.shoes.RShoes.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Photo {

    private Long photoNo;       // 사진번호
    private Shoes shoes;        // 신발
    private String originalName;  // 원본명
    private String serverName;   // 서버파일명

    public Photo(Shoes shoes, String originalName, String serverName){
        this.shoes = shoes;
        this.originalName = originalName;
        this.serverName = serverName;
    }
}
