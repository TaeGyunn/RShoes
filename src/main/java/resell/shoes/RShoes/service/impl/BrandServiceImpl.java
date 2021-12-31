package resell.shoes.RShoes.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resell.shoes.RShoes.dto.BrandDTO;
import resell.shoes.RShoes.repository.BrandRepository;
import resell.shoes.RShoes.service.BrandService;
import resell.shoes.RShoes.util.Response;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final Response response;

    @Override
    public ResponseEntity<?> insertBrand(BrandDTO brandDTO) {

        Map<String,Boolean> map = new HashMap<>();

        if(brandRepository.checkByName(brandDTO.getBrandName())){
            map.put("insert", false);
            return response.fail(map, "브랜드가 이미 있습니다", HttpStatus.BAD_REQUEST);
        }

        brandRepository.insertBrand(brandDTO);
        map.put("insert", true);
        return response.success(map, "브랜드 생성 성공하였습니다", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> deleteBrand(BrandDTO brandDTO) {

        Map<String,Boolean> map = new HashMap<>();

        if(!brandRepository.checkByName(brandDTO.getBrandName())){
            map.put("delete", false);
            return response.fail(map, "브랜드가 없습니다", HttpStatus.BAD_REQUEST);
        }

        brandRepository.deleteBrand(brandDTO);
        map.put("delete", true);
        return response.fail(map, "브랜드 삭제 성공하였습니다", HttpStatus.OK);

    }
}
