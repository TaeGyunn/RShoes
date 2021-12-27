package resell.shoes.RShoes.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import resell.shoes.RShoes.dto.InsertShoesDTO;
import resell.shoes.RShoes.dto.ModifyShoesDTO;
import resell.shoes.RShoes.entity.Brand;
import resell.shoes.RShoes.entity.Category;
import resell.shoes.RShoes.entity.Photo;
import resell.shoes.RShoes.entity.Shoes;
import resell.shoes.RShoes.repository.BrandRepository;
import resell.shoes.RShoes.repository.CategoryRepository;
import resell.shoes.RShoes.repository.PhotoRepository;
import resell.shoes.RShoes.repository.ShoesRepository;
import resell.shoes.RShoes.service.S3Service;
import resell.shoes.RShoes.service.ShoesService;
import resell.shoes.RShoes.util.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ShoesServiceImpl implements ShoesService {

    private final ShoesRepository shoesRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final PhotoRepository photoRepository;
    private final S3Service s3Service;
    private final Response response;

    @Override
    public ResponseEntity<?> insertShoes(InsertShoesDTO insertShoesDTO, List<MultipartFile> files) {

        Map<String, String> map = new HashMap<>();

        Category category = categoryRepository.findByName(insertShoesDTO.getCategory());
        if(category == null){
            map.put("insert", "fail");
            return response.fail(map, "카테고리를 찾을 수 없습니다", HttpStatus.BAD_REQUEST);
        }
        Brand brand = brandRepository.findByName(insertShoesDTO.getBrand());
        if(brand == null){
            map.put("insert", "fail");
            return response.fail(map, "브랜드를 찾을 수 없습니다", HttpStatus.BAD_REQUEST);
        }

        Shoes shoes = new Shoes(brand,
                category,
                insertShoesDTO.getName(),
                insertShoesDTO.getPrice(),
                insertShoesDTO.getColor(),
                insertShoesDTO.getSize());

       shoesRepository.insertShoes(shoes);
       log.info("sno : "+ shoes.getShoesNo());

       shoes.setShoesNo(shoes.getShoesNo());

      List<Photo> photos =  s3Service.uploadFile(files, shoes);

      for(Photo photo : photos){
          photoRepository.insertPhoto(photo);
      }

      map.put("insert", "success");
      return response.success(map, "신발 추가 성공하였습니다", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> modifyShoes(ModifyShoesDTO modifyShoesDTO, List<MultipartFile> files) {

        Map<String, String> map = new HashMap<>();

        Category category = categoryRepository.findByName(modifyShoesDTO.getCategory());
        if(category == null){
            map.put("insert", "fail");
            return response.fail(map, "카테고리를 찾을 수 없습니다", HttpStatus.BAD_REQUEST);
        }
        Brand brand = brandRepository.findByName(modifyShoesDTO.getBrand());
        if(brand == null){
            map.put("insert", "fail");
            return response.fail(map, "브랜드를 찾을 수 없습니다", HttpStatus.BAD_REQUEST);
        }

        Shoes shoes = new Shoes(
                modifyShoesDTO.getShoesNo(),
                brand,
                category,
                modifyShoesDTO.getName(),
                modifyShoesDTO.getPrice(),
                modifyShoesDTO.getColor(),
                modifyShoesDTO.getSize());

        shoesRepository.modifyShoes(shoes);


        shoes.setShoesNo(modifyShoesDTO.getShoesNo());


        if(files != null){
            List<Photo> photos = photoRepository.findBySno(modifyShoesDTO.getShoesNo());
            for(Photo photo : photos){
                s3Service.delete(photo.getServerName());
                photoRepository.deletePhoto(photo.getPhotoNo());
            }

            photos =  s3Service.uploadFile(files, shoes);

            for(Photo photo : photos){
                photo.setShoes(shoes);
                photoRepository.insertPhoto(photo);
            }

        }

        map.put("modify", "success");
        return response.success(map, "신발 수정 성공하였습니다", HttpStatus.OK);
    }
}
