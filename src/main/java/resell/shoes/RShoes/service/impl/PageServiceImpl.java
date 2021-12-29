package resell.shoes.RShoes.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resell.shoes.RShoes.dto.PageShoesDTO;
import resell.shoes.RShoes.entity.Photo;
import resell.shoes.RShoes.repository.PageRepository;
import resell.shoes.RShoes.repository.PhotoRepository;
import resell.shoes.RShoes.service.PageService;
import resell.shoes.RShoes.service.S3Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;
    private final PhotoRepository photoRepository;
    private final S3Service s3Service;

    @Override
    public Page<PageShoesDTO> getAllShoes(int page) {
        PageHelper.startPage(page, 10);

        Page<PageShoesDTO> getShoes = pageRepository.getAllShoesDTO(page);

        for(PageShoesDTO pageShoesDTO : getShoes){
            List<Photo> photos = photoRepository.findBySno(pageShoesDTO.getShoesNo());
            List<String> url = new ArrayList<>();
            for(Photo photo : photos){
                url.add(s3Service.getFileUrl(photo.getServerName()));
            }
            pageShoesDTO.setUrl(url);
        }
        return getShoes;
    }

}
