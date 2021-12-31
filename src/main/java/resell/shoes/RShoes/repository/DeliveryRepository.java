package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.entity.Delivery;
import resell.shoes.RShoes.entity.Shoes;

@Repository
public interface DeliveryRepository {

    //배달지 추가
    Long insertDelivery(Delivery delivery);

    //delivery 가져오기
    Delivery findByDno(Long dno);
}
