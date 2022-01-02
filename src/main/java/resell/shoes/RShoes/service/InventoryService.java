package resell.shoes.RShoes.service;

import org.springframework.http.ResponseEntity;
import resell.shoes.RShoes.dto.InventoryDTO;

public interface InventoryService {

    ResponseEntity<?> checkInventory(InventoryDTO inventory);
}
