package africa.semicolon.Services;

import africa.semicolon.data.models.UserGender;
import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dtos.requests.RegisterUserRequest;
import africa.semicolon.dtos.responses.RegisterUserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        RegisterUserResponse response = UserService.registerUser(registerUserRequest);
        assertNotNull(response);
        assertEquals("Register Success", response.getMessage());
    }

}