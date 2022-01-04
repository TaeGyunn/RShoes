package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.dto.JoinDTO;
import resell.shoes.RShoes.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository {
    
    // id로 유저 찾기
    Optional<User> findById(String id);
    
    // id 중복검사
    Boolean checkById(String id);
    
    // email 중복검사
    Boolean checkByEmail(String email);
    
    // number 중복검사
    Boolean checkByPhone(String phone);
    
    // 유저 회원가입
    Integer join(JoinDTO join);

    // 이메일, 이름 확인
    Optional<User> findByEmailAndName(String email, String name);

    //이메일로 user찾기
    Optional<User> findByEmail(String email);

    // 비밀번호 변경하기
    void updatePw(String pw, String id);

    // 이메일, Id 확인
    Boolean checkByEmailAndId(String email, String userId);
}
