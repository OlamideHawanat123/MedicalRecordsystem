package africa.semicolon.Services;

import africa.semicolon.Exceptions.EmailAlreadyExistException;
import africa.semicolon.Exceptions.EmptyDetailsException;
import africa.semicolon.Utils.Mapper;
import africa.semicolon.data.models.Patient;
import africa.semicolon.data.models.User;
import africa.semicolon.data.models.UserRoles;
import africa.semicolon.data.repositories.PatientRepository;
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
    private PatientRepository patientRepository;
    @Autowired
    public UserServiceImplementation(UserRepository userRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }


    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        User user = Mapper.mapRequestToUser(registerUserRequest,passwordEncoder);
        Patient patient = Mapper.mapRequestToPatient(user);

        if(emptyEmailAndPassword(registerUserRequest.getEmail(), registerUserRequest.getPassword()))throw new EmptyDetailsException("Email or password cannot be empty");
        if(userRepository.existsByEmail(registerUserRequest.getEmail().trim().toLowerCase()))throw new EmailAlreadyExistException("Email already exist");
        EmailService emailService = new EmailService();
//        emailService.sendVerificationEmail(registerUserRequest.getEmail().trim().toLowerCase());
        if(isPatient(registerUserRequest.getRole())) patientRepository.save(patient);
        userRepository.save(user);

        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("User registered successfully");
        registerUserResponse.setId(user.getId());
        return registerUserResponse;
    }

    private boolean emptyEmailAndPassword(String email, String password){
        return email.isEmpty() || password.isEmpty();
    }

    private boolean isPatient(UserRoles role){
        return role == UserRoles.PATIENT;
    }
}
