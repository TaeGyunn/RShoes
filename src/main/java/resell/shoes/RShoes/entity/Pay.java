package resell.shoes.RShoes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class Pay {

    private Long payNo;                     //페이no

    private String name;                    //PG사

    private String type;                    //결제수단

    private String content;                 //결제내용

    private LocalDateTime payCreateTime;    //결제일자
}
