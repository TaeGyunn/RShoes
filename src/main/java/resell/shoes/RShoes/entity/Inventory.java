package resell.shoes.RShoes.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import resell.shoes.RShoes.dto.Status;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Inventory {

    private Long inventoryNo;

    private Status inventoryStatus; //상태

    private String manager;

    private LocalDateTime InspectionTime;

    public Inventory(Long inventoryNo, Status inventoryStatus, String manager) {
        this.inventoryNo = inventoryNo;
        this.inventoryStatus = inventoryStatus;
        this.manager = manager;
    }

    public Inventory(Status inventoryStatus){
        this.inventoryStatus = inventoryStatus;
    }


}
