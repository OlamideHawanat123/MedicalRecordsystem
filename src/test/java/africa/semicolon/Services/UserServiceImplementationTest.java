package africa.semicolon.Services;

import africa.semicolon.Exceptions.EmailAlreadyExistException;
import africa.semicolon.Exceptions.EmptyDetailsException;
import africa.semicolon.data.models.UserGender;
import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dtos.requests.RegisterUserRequest;
import africa.semicolon.dtos.responses.RegisterUserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class UserServiceImplementationTest {
    @Autowired
    private UserService userService;

    private UserRepository userRepository;

    @Test
    public void testThatUserRegistrationRequestRegistersUser(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setAge(20);
        registerUserRequest.setAddress("3, Ebute Olowo Street, Okepopo");
        registerUserRequest.setPhone("123456789");
        registerUserRequest.setGender(UserGender.MALE);
        registerUserRequest.setEmail("olamide@gmail.com");
        registerUserRequest.setPassword("password");
        registerUserRequest.setLastName("Olamide");
        registerUserRequest.setFirstName("Olamide");
        RegisterUserResponse response = userService.registerUser(registerUserRequest);
        assertNotNull(response);
        assertEquals("User registered successfully", response.getMessage());
    }

    @Test
    public void testThatUserRegistrationThrowsAnExceptionIfYouRegisterWithTheSameEmail(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setAge(20);
        registerUserRequest.setAddress("3, Ebute Olowo Street, Okepopo");
        registerUserRequest.setPhone("123456789");
        registerUserRequest.setGender(UserGender.MALE);
        registerUserRequest.setEmail("olamide@gmail.com");
        registerUserRequest.setPassword("password");
        registerUserRequest.setLastName("Olamide");
        registerUserRequest.setFirstName("Olamide");

        assertThrows(EmailAlreadyExistException.class, () ->{
            userService.registerUser(registerUserRequest);
        });
    }

    @Test
    public void testThatUserRegistrationThrowsAnExceptionIfEmailAndPasswordFieldsAreNull(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setAge(20);
        registerUserRequest.setAddress("3, Ebute Olowo Street, Okepopo");
        registerUserRequest.setPhone("123456789");
        registerUserRequest.setGender(UserGender.MALE);
        registerUserRequest.setEmail("");
        registerUserRequest.setPassword("");
        registerUserRequest.setLastName("Olamide");
        registerUserRequest.setFirstName("Olamide");

        assertThrows(EmptyDetailsException.class, () -> {
            userService.registerUser(registerUserRequest);
        });
    
    }

}