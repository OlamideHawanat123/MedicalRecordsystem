package africa.semicolon.Services;

import africa.semicolon.Exceptions.EmailAlreadyExistException;
import africa.semicolon.Exceptions.EmptyDetailsException;
import africa.semicolon.Exceptions.FailedVerificationException;
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
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationService verificationService;

    private final UserRepository userRepository;
    private PatientRepository patientRepository;
    @Autowired
    public UserServiceImplementation(UserRepository userRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        User user = Mapper.mapRequestToUser(registerUserRequest,passwordEncoder);
        Patient patient = Mapper.mapRequestToPatient(user);

        if(emptyEmailAndPassword(registerUserRequest.getEmail(), registerUserRequest.getPassword()))throw new EmptyDetailsException("Email or password cannot be empty");
        if(userRepository.existsByEmail(registerUserRequest.getEmail().trim().toLowerCase()))throw new EmailAlreadyExistException("Email already exist");
        if(isPatient(registerUserRequest.getRole())) patientRepository.save(patient);
        userRepository.save(user);

        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("User registered successfully");
        registerUserResponse.setId(user.getId());

        verificationService.sendVerification(registerUserRequest.getEmail());
        return registerUserResponse;
    }

    public boolean confirm(String email, String code){
        boolean valid = verificationService.verifyConfirmationCode(email, code);
        if(!valid) throw new FailedVerificationException("Verification code does not match!");

        Optional<User> findUser = userRepository.findByEmail(email);
        if(findUser.isPresent()){
            User user = findUser.get();
            user.setVerified(true);
            userRepository.save(user);
        }
        return true;
    }

    private boolean emptyEmailAndPassword(String email, String password){
        return email == null || email.isBlank() || password == null || password.isBlank();
    }

    private boolean isPatient(UserRoles role){
        return role == UserRoles.PATIENT;
    }
}
