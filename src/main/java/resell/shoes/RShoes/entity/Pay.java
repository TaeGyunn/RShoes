package resell.shoes.RShoes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class Pay {

    private Long payNo;                     //페이no

    private String payName;                    //PG사

    private String payType;                    //결제수단

    private String payContent;                 //결제내용

    private LocalDateTime payCreateTime;    //결제일자

    public Pay(String payName, String payType, LocalDateTime payCreateTime, String content) {
        this.payName = payName;
        this.payType = payType;
        this.payCreateTime = payCreateTime;
        this.payContent = content;
    }

    public void setPayNo(Long payNo){
        this.payNo = payNo;
    }
}
