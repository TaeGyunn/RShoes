package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.entity.Photo;

@Repository
public interface PhotoRepository {

    Integer insertPhoto(Photo photo);
}
