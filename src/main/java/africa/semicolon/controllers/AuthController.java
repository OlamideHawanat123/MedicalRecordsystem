package africa.semicolon.controllers;

import africa.semicolon.Exceptions.EmailAlreadyExistException;
import africa.semicolon.Exceptions.UserNotFound;
import africa.semicolon.Services.UserService;
import africa.semicolon.dtos.requests.RegisterUserRequest;
import africa.semicolon.dtos.requests.UserLoginRequest;
import africa.semicolon.dtos.responses.RegisterUserResponse;
import africa.semicolon.dtos.responses.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")

public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterUserRequest request){
        try{
            RegisterUserResponse response = userService.registerUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(EmailAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request){
        try{
            UserLoginResponse response = userService.logUserIn(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(UserNotFound e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
