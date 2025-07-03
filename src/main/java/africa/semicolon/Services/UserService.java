package africa.semicolon.Services;

import africa.semicolon.dtos.requests.RegisterUserRequest;
import africa.semicolon.dtos.responses.RegisterUserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest)

}
