package resell.shoes.RShoes.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import resell.shoes.RShoes.entity.Photo;
import resell.shoes.RShoes.entity.Shoes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class S3Service {

    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public List<Photo> uploadFile(List<MultipartFile> files, Shoes shoes){

        List<Photo> photos = new ArrayList<>();

        for(MultipartFile file : files){

            String fileName = createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            try(InputStream inputStream = file.getInputStream()){
                this.s3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream,objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            }catch(IOException e){
                throw new IllegalArgumentException("파일 변환 중 에러가 발생하였습니다.");
            }

            Photo photo = new Photo(shoes, file.getOriginalFilename(), fileName);
            photos.add(photo);
        }

        return photos;
    }


    private String createFileName(String originalFileName){
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    private String getFileExtension(String fileName){
        try{
            return fileName.substring(fileName.lastIndexOf("."));
        }catch (StringIndexOutOfBoundsException e){
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 (%s)입니다.", fileName));
        }
    }

    public String getFileUrl(String fileName){

        return s3Client.getUrl(bucketName, fileName).toString();
    }

    public void delete(String fileName){
        try{
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.bucketName, fileName);
            this.s3Client.deleteObject(deleteObjectRequest);
        }catch (AmazonServiceException e){
            e.printStackTrace();
        }
    }
}
