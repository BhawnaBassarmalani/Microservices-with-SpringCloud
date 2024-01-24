package spring.bootrestfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.bootrestfulwebservices.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {


}
