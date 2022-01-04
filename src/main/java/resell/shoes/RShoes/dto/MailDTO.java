package resell.shoes.RShoes.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MailDTO {

    private String address;
    private String title;
    private String message;
}
