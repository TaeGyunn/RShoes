package resell.shoes.RShoes.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderGetDTO {

    private Long orderNo;
    private Long shoesNo;
    private String shoesName;
    private Integer price;
    private Integer size;
    private LocalDateTime date;
    private Status status;
    private List<String> url;
}
