package africa.semicolon.Services;

import africa.semicolon.Exceptions.*;
import africa.semicolon.Utils.Mapper;
import africa.semicolon.data.models.*;
import africa.semicolon.data.repositories.*;
import africa.semicolon.dtos.requests.LodgeComplaintRequest;
import africa.semicolon.dtos.requests.RegisterUserRequest;
import africa.semicolon.dtos.requests.UserLoginRequest;
import africa.semicolon.dtos.responses.LodgeComplaintResponse;
import africa.semicolon.dtos.responses.RegisterUserResponse;
import africa.semicolon.dtos.responses.UserLoginResponse;
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
    private PendingDoctorRepository pendingDoctorRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private SuperAdminRepo superAdminRepo;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, PatientRepository patientRepository, PendingDoctorRepository pendingDoctorRepository) {
        this.pendingDoctorRepository = pendingDoctorRepository;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        validateRequest(registerUserRequest);
        User user = Mapper.mapRequestToUser(registerUserRequest,passwordEncoder);

        if(isPatient(registerUserRequest.getRole()))return registerPatient(user);
        else if(isDoctor(registerUserRequest.getRole()))return  registerDoctor(user);
        else if(isAdmin(registerUserRequest.getRole())) return registerAdmin(user);
        else if(isSuperAdmin(registerUserRequest.getRole()))return registerSuperAdmin(user);
        verificationService.sendVerification(user.getEmail());

        throw new InvalidRoleException("Invalid user role: " + registerUserRequest.getRole());
    }

    @Override
    public UserLoginResponse logUserIn(UserLoginRequest login) {
        if(emptyEmailAndPassword(login.getEmail(), login.getPassword()))throw new EmptyDetailsException("Email or password cannot be empty");
        if(!userRepository.existsByEmail(login.getEmail())) throw new UserNotFound("User doesn't exist");

        User user = userRepository.findByEmail(login.getEmail().trim().toLowerCase()).orElseThrow();
        if(!passwordEncoder.matches(login.getPassword(), user.getPassword())) throw new InvalidCredentialsException("Invalid details!");

        UserLoginResponse loginResponse = new UserLoginResponse();
        loginResponse.setMessage("login successful!");
        return loginResponse;
    }

    @Override
    public LodgeComplaintResponse lodgeComplaint(LodgeComplaintRequest request) {
        return null;
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

    private boolean isAdmin(UserRoles role) {
        return role == UserRoles.ADMIN;
    }

    private boolean isDoctor(UserRoles role){
        return role == UserRoles.DOCTOR;
    }

    private boolean isSuperAdmin(UserRoles role) {
        return role == UserRoles.SUPER_ADMIN;
    }

    private void validateRequest(RegisterUserRequest registerUserRequest) {
        String email = registerUserRequest.getEmail();
        String password = registerUserRequest.getPassword();

        if(emptyEmailAndPassword(email, password))throw new EmptyDetailsException("Email or password cannot be empty");
        if(userRepository.existsByEmail(email))throw new EmailAlreadyExistException("Email already exist");
    }

    private RegisterUserResponse registerPatient(User user) {
        Patient patient = Mapper.mapUserToPatient(user);
        patientRepository.save(patient);
        userRepository.save(patient);


        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("User registered successfully");
        registerUserResponse.setId(user.getId());
        return registerUserResponse;
    }
    private boolean verifiedDoctor(boolean isVerified){
        return isVerified;
    }

    private RegisterUserResponse registerDoctor(User user) {
        userRepository.save(user);
        notifyAdminOfRequest(user);

        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("Pending Registration, awaiting admin's approval");
        registerUserResponse.setId(user.getId());
        return registerUserResponse;
    }

    private void notifyAdminOfRequest(User user) {
        Doctors doctor = Mapper.mapUserToDoctors(user);
        pendingDoctorRepository.save(doctor);
    }

    private RegisterUserResponse registerAdmin(User user){
        userRepository.save(user);
        notifySuperAdminOfRequest(user);

        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("Registration successful, waiting for approval");
        registerUserResponse.setId(user.getId());
        return registerUserResponse;
    }

    private void notifySuperAdminOfRequest(User user) {
        Admin admin = Mapper.mapUsersToAdmin(user);
        adminRepository.save(admin);
    }

    private RegisterUserResponse registerSuperAdmin(User user){
        if(superAdminRepo.count() == 1) throw new FailedRoleException("You should choose to register for another role instead");
        SuperAdmin superAdmin = Mapper.mapUsersToSuperAdmin(user);
        userRepository.save(user);
        superAdminRepo.save(superAdmin);

        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("Registration successful, superAdmin!");
        registerUserResponse.setId(user.getId());
        return registerUserResponse;
    }

}
