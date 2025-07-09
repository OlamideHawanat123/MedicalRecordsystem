package africa.semicolon.Services;

import africa.semicolon.dtos.requests.RegisterUserRequest;
import africa.semicolon.dtos.requests.UserLoginRequest;
import africa.semicolon.dtos.responses.RegisterUserResponse;
import africa.semicolon.dtos.responses.UserLoginResponse;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);

    UserLoginResponse logUserIn(UserLoginRequest login);


}
