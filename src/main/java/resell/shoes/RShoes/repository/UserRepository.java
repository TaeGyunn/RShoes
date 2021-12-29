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
}
