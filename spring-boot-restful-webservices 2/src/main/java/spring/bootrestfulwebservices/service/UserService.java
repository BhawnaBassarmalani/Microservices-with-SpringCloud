package spring.bootrestfulwebservices.service;

import spring.bootrestfulwebservices.dto.UserDto;
import spring.bootrestfulwebservices.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);

    void deleteUser(Long userId);
}
