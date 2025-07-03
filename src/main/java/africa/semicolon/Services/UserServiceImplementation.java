package africa.semicolon.Services;

import africa.semicolon.dtos.requests.RegisterUserRequest;
import africa.semicolon.dtos.responses.RegisterUserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        registerUserRequest.setFirstName(registerUserRequest.getFirstName());
        registerUserRequest.setLastName(registerUserRequest.getLastName());
        registerUserRequest.setEmail(registerUserRequest.getEmail());
        registerUserRequest.setPassword(registerUserRequest.getPassword());
        registerUserRequest.setPhone(registerUserRequest.getPhone());
        registerUserRequest.setGender(registerUserRequest.getGender());
        registerUserRequest.setAddress(registerUserRequest.getAddress());
        registerUserRequest.setAddress(registerUserRequest.getAddress());
    }
}
