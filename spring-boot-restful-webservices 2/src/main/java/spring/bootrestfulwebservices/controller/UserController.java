package spring.bootrestfulwebservices.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;
import spring.bootrestfulwebservices.entity.User;
import spring.bootrestfulwebservices.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;
@PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
       User savedUser =  userService.createUser(user);
       return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }

    // build get user by id REST API
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
    User user = userService.getUserById(userId);
    return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
    List<User> users = userService.getAllUsers();
    return new ResponseEntity<>(users,HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id")Long userId ,@RequestBody User user){
    user.setId(userId);
      User updatedUser =  userService.updateUser(user);
      return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    // delete REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id")Long userId){
    userService.deleteUser(userId);
    return new ResponseEntity<>("user successfully deleted" , HttpStatus.OK);
    }
}
