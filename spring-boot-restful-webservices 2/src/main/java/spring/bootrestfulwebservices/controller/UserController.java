package spring.bootrestfulwebservices.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import spring.bootrestfulwebservices.dto.UserDto;
import spring.bootrestfulwebservices.entity.User;
import spring.bootrestfulwebservices.exception.ErrorDetails;
import spring.bootrestfulwebservices.exception.ResourceNotFoundException;
import spring.bootrestfulwebservices.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;
@PostMapping
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto user){
       UserDto savedUser =  userService.createUser(user);
       return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }

    // build get user by id REST API
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
    UserDto user = userService.getUserById(userId);
    return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
    List<UserDto> users = userService.getAllUsers();
    return new ResponseEntity<>(users,HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id")Long userId ,@RequestBody @Valid UserDto user){
    user.setId(userId);
      UserDto updatedUser =  userService.updateUser(user);
      return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    // delete REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id")Long userId){
    userService.deleteUser(userId);
    return new ResponseEntity<>("user successfully deleted" , HttpStatus.OK);
    }
//@ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception ,
//                                                                        WebRequest webRequest){
//
//    ErrorDetails errorDetails = new ErrorDetails(
//            LocalDateTime.now(),
//            exception.getMessage(),
//            webRequest.getDescription(false),
//            "USER_NOT_FOUND"
//    );
//
//    return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//
//
//
//    }
}
