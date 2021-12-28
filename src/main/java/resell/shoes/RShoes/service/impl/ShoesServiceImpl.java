package resell.shoes.RShoes.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Server;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import resell.shoes.RShoes.dto.DeleteShoesDTO;
import resell.shoes.RShoes.dto.InsertShoesDTO;
import resell.shoes.RShoes.dto.ModifyShoesDTO;
import resell.shoes.RShoes.entity.*;
import resell.shoes.RShoes.repository.*;
import resell.shoes.RShoes.service.S3Service;
import resell.shoes.RShoes.service.ShoesService;
import resell.shoes.RShoes.util.Response;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ShoesServiceImpl implements ShoesService {

    private final ShoesRepository shoesRepository;
    private final UserRepository userRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final PhotoRepository photoRepository;
    private final S3Service s3Service;
    private final Response response;

    @Override
    public ResponseEntity<?> insertShoes(InsertShoesDTO insertShoesDTO, List<MultipartFile> files) {

        Map<String, Boolean> map = new HashMap<>();

        Category category = categoryRepository.findByName(insertShoesDTO.getCategory());
        if(category == null){
            map.put("insert", false);
            return response.fail(map, "카테고리를 찾을 수 없습니다", HttpStatus.BAD_REQUEST);
        }
        Brand brand = brandRepository.findByName(insertShoesDTO.getBrand());
        if(brand == null){
            map.put("insert", false);
            return response.fail(map, "브랜드를 찾을 수 없습니다", HttpStatus.BAD_REQUEST);
        }

        Shoes shoes = new Shoes(brand,
                category,
                insertShoesDTO.getName(),
                insertShoesDTO.getPrice(),
                insertShoesDTO.getColor(),
                insertShoesDTO.getSize());

       shoesRepository.insertShoes(shoes);

       shoes.setShoesNo(shoes.getShoesNo());

      List<Photo> photos =  s3Service.uploadFile(files, shoes);

      for(Photo photo : photos){
          photoRepository.insertPhoto(photo);
      }

      map.put("insert", true);
      return response.success(map, "신발 추가 성공하였습니다", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> modifyShoes(ModifyShoesDTO modifyShoesDTO, List<MultipartFile> files) {

        Map<String, Boolean> map = new HashMap<>();

        Category category = categoryRepository.findByName(modifyShoesDTO.getCategory());
        if(category == null){
            map.put("insert", false);
            return response.fail(map, "카테고리를 찾을 수 없습니다", HttpStatus.BAD_REQUEST);
        }
        Brand brand = brandRepository.findByName(modifyShoesDTO.getBrand());
        if(brand == null){
            map.put("insert", false);
            return response.fail(map, "브랜드를 찾을 수 없습니다", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findById(modifyShoesDTO.getUserId()).orElse(null);
        if(user == null) {
            map.put("delete", false);
            return response.fail(map, "유저id를 확인해주세요", HttpStatus.BAD_REQUEST);
        }
        if(brand.getUser().getUserNo() != user.getUserNo()){
            map.put("delete", false);
            return response.fail(map, "유저의 상품이 아닙니다.", HttpStatus.BAD_REQUEST);
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

        map.put("modify", true);
        return response.success(map, "신발 수정 성공하였습니다", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteShoes(DeleteShoesDTO shoesDTO) {

        Map<String, Boolean> map = new HashMap<>();

        Shoes shoes = shoesRepository.findByShoesNo(shoesDTO.getShoesNo());
        if(shoes == null){
            map.put("delete", false);
            return response.fail(map, "상품을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findById(shoesDTO.getUserId()).orElse(null);
        if(user == null) {
            map.put("delete", false);
            return response.fail(map, "유저id를 확인해주세요", HttpStatus.BAD_REQUEST);
        }
        if(!brandRepository.checkByUserIdAndBno(shoes.getBrand().getBrandNo(), user.getUserNo())){
            map.put("delete", false);
            return response.fail(map, "유저의 상품이 아닙니다.", HttpStatus.BAD_REQUEST);
        }

        List<Photo> photos = photoRepository.findBySno(shoesDTO.getShoesNo());
        for(Photo photo : photos){
            s3Service.delete(photo.getServerName());
            photoRepository.deletePhoto(photo.getPhotoNo());
        }

        shoesRepository.deleteShoes(shoesDTO.getShoesNo());
        map.put("delete", true);
        return response.success(map, "상품 삭제가 완료되었습니다.", HttpStatus.OK);
    }

}
