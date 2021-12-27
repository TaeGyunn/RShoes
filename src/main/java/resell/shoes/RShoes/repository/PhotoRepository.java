package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.entity.Photo;

import java.util.List;

@Repository
public interface PhotoRepository {

    Integer insertPhoto(Photo photo);

    List<Photo> findBySno(Long shoesNo);

    void deletePhoto(Long photoNo);

}
