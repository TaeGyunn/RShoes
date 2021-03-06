package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.dto.InventoryDTO;
import resell.shoes.RShoes.entity.Inventory;

@Repository
public interface InventoryRepository {

    Long insertInventory(Inventory inventory);

    void modifyStatus(Inventory inventory);

    void modifyStatus2(Long ino, String status);
}
