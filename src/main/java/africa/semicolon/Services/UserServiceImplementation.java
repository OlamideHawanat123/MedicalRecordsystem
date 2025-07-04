package africa.semicolon.Services;

import africa.semicolon.Exceptions.EmailAlreadyExistException;
import africa.semicolon.Exceptions.EmptyDetailsException;
import africa.semicolon.Utils.Mapper;
import africa.semicolon.data.models.User;
import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dtos.requests.RegisterUserRequest;
import africa.semicolon.dtos.responses.RegisterUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        User user;
        user = Mapper.mapRequestToUser(registerUserRequest,passwordEncoder);

        if(emptyEmailAndPassword(registerUserRequest.getEmail(), registerUserRequest.getPassword()))throw new EmptyDetailsException("Email or password cannot be empty");
        if(userRepository.existsByEmail(registerUserRequest.getEmail()))throw new EmailAlreadyExistException("Email already exist");

        userRepository.save(user);
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("User registered successfully");
        return registerUserResponse;
    }

    private boolean emptyEmailAndPassword(String email, String password){
        return email.isEmpty() || password.isEmpty();
    }
}
