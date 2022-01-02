package resell.shoes.RShoes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    //배송
    READY("READY"), //준비
    ARRIVE("ARRIVE"),   //도착
    RETURN("RETURN"),
    //창고
    Inspection("INSPECTION"),   //검수
    FAIL("FAIL"),
    PASS("PASS");

    private final String value;
}
