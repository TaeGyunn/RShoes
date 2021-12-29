package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.entity.Photo;

import java.util.List;

@Repository
public interface PhotoRepository {
    
    // 사진추가
    Integer insertPhoto(Photo photo);
    
    //신발에 대한 사진 가져오기
    List<Photo> findBySno(Long shoesNo);
    
    //사진삭제
    void deletePhoto(Long photoNo);

}
