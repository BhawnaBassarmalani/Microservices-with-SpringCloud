package spring.bootrestfulwebservices.service.Impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spring.bootrestfulwebservices.dto.UserDto;
import spring.bootrestfulwebservices.entity.User;
import spring.bootrestfulwebservices.exception.EmailAlreadyExistsException;
import spring.bootrestfulwebservices.exception.ResourceNotFoundException;
import spring.bootrestfulwebservices.mapper.UserMapper;
import spring.bootrestfulwebservices.repository.UserRepository;
import spring.bootrestfulwebservices.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
       // User user = UserMapper.mapToUser(userDto);

        Optional<User> optionalUser  = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = modelMapper.map(userDto,User.class);

        // convert UserDto into User JPA entity
        User savedUser = userRepository.save(user);

        UserDto saveduserDto = modelMapper.map(savedUser,UserDto.class);
        // convert JPA entity to userDto
        return saveduserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User" , "id" , userId)
        );

        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
       // return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        return users.stream().map((user)->modelMapper.map(users , UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("User" , "id" , user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.mapToUserDto(updatedUser);

    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User" , "id" , userId)
        );
        userRepository.deleteById(userId);
    }

}
