package resell.shoes.RShoes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import resell.shoes.RShoes.dto.FindPwDTO;
import resell.shoes.RShoes.dto.MailDTO;
import resell.shoes.RShoes.entity.User;
import resell.shoes.RShoes.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String FROM_ADDRESS;

    public MailDTO createMailAndChangePassword(FindPwDTO findPwDTO){
        String str = getTempPassword();
        MailDTO DTO = new MailDTO();
        DTO.setAddress(findPwDTO.getEmail());
        DTO.setTitle(findPwDTO.getName() + "님의 RShoes 임시비밀번호 안내 이메일 입니다.");
        DTO.setMessage("안녕하세요. RShoes 임시비밀번호 안내 관련 이메일 입니다." + System.lineSeparator() +"[ " + findPwDTO.getName() + " ]" +"님의 임시 비밀번호는 "
                + str + " 입니다.");
        updatePassword(str, findPwDTO.getEmail());
        return DTO;
    }

    public void updatePassword(String str, String userEmail){
        String pw = passwordEncoder.encode(str);
        User user = userRepository.findByEmail(userEmail).orElse(null);
        userRepository.updatePw(user.getPw(), user.getId());
    }

    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void sendMail(MailDTO mailDTO){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_ADDRESS);
        message.setTo(mailDTO.getAddress());
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());
        mailSender.send(message);
    }




}
