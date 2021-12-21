package resell.shoes.RShoes.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class JoinDTO {
    
    @NotBlank(message = "id를 입력해주세요")
    private String id;

    @NotBlank(message = "pw를 입력해주세요")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요")
    private String pw;
    
    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "올바른 이메일 주소를 입력해주세요")
    private String email;

    @NotBlank(message = "핸드폰 번호를 입력해주세요")
    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "휴대폰 번호 형식에 맞지 않습니다")
    private String phone;

    private String bank;

    private String account;
}
