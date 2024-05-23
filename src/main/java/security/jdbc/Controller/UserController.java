package security.jdbc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import security.jdbc.Exception.CreationFailedException;
import security.jdbc.Exception.LoginFailedException;
import security.jdbc.Model.User;
import security.jdbc.Service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User user){
        try{
            userService.register(user);
            return new ResponseEntity<>("User created", HttpStatus.CREATED);
        }catch(CreationFailedException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try{
            return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
        }catch(LoginFailedException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
