package spring.bootrestfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.bootrestfulwebservices.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);


}
