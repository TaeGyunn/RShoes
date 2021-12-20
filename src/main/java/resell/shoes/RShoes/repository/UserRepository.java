package resell.shoes.RShoes.repository;

import org.springframework.stereotype.Repository;
import resell.shoes.RShoes.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository {

    public Optional<User> findById(String id);


}
