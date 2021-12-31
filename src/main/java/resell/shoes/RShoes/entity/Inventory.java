package resell.shoes.RShoes.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Inventory {

    private Long inventoryNo;

    private String inventoryStatus; //상태

    public Inventory(String inventoryStatus){
        this.inventoryStatus = inventoryStatus;
    }


}
