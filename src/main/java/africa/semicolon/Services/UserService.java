package africa.semicolon.Services;

import africa.semicolon.dtos.requests.*;
import africa.semicolon.dtos.responses.LodgeComplaintResponse;
import africa.semicolon.dtos.responses.RegisterUserResponse;
import africa.semicolon.dtos.responses.UserLoginResponse;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);

    UserLoginResponse logUserIn(UserLoginRequest login);

    ConfirmRegistrationCodeResponse confirmVerification(ConfirmRegistrationCodeRequest request);

//    ConfirmRegistrationCodeResponse confirmVerification(String code, String email);
}
