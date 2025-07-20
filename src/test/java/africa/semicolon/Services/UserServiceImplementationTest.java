package africa.semicolon.Services;

import africa.semicolon.Exceptions.EmailAlreadyExistException;
import africa.semicolon.Exceptions.EmptyDetailsException;
import africa.semicolon.Exceptions.FailedRoleException;
import africa.semicolon.Exceptions.UserNotFound;
import africa.semicolon.data.models.UserGender;
import africa.semicolon.data.models.UserRoles;
import africa.semicolon.data.repositories.*;
import africa.semicolon.dtos.requests.RegisterUserRequest;
import africa.semicolon.dtos.requests.UserLoginRequest;
import africa.semicolon.dtos.responses.RegisterUserResponse;
import africa.semicolon.dtos.responses.UserLoginResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class UserServiceImplementationTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository  patientRepository;
    @Autowired
    private PendingDoctorRepository pendingDoctorRepository;

    @Autowired
    private SuperAdminRepo superAdminRepo;

    @Test
    public void testThatUserRegistrationRequestRegistersUser() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setAge(20);
        registerUserRequest.setAddress("3, Ebute Olowo Street, Okepop o");
        registerUserRequest.setPhone("123456789");
        registerUserRequest.setGender(UserGender.MALE);
        registerUserRequest.setEmail("aladeamidatayomide@gmail.com");
        registerUserRequest.setPassword("password");
        registerUserRequest.setLastName("Olamide");
        registerUserRequest.setFirstName("Olamide");
        registerUserRequest.setRole(UserRoles.PATIENT);
        RegisterUserResponse response = userService.registerUser(registerUserRequest);
        assertNotNull(response);
        assertEquals("User registered successfully", response.getMessage());
        assertTrue(userRepository.existsByEmail(registerUserRequest.getEmail()));
    }

    @Test
    public void testThatUserThatRegistersAsDoctorGetsTheirRequestSavedInAPendingRepositoryBeforeApproval(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setAge(20);
        registerUserRequest.setAddress("3, Ebute Olowo Street, Okepop o");
        registerUserRequest.setPhone("123456789");
        registerUserRequest.setGender(UserGender.FEMALE);
        registerUserRequest.setEmail("bellohackim@gmail.com");
        registerUserRequest.setPassword("password");
        registerUserRequest.setLastName("Olamide");
        registerUserRequest.setFirstName("Olamide");
        registerUserRequest.setRole(UserRoles.DOCTOR);

        RegisterUserResponse response = userService.registerUser(registerUserRequest);
        assertNotNull(response);
        assertEquals("Pending Registration, awaiting admin's approval", response.getMessage());
        assertTrue(pendingDoctorRepository.existsByEmail(registerUserRequest.getEmail()));

    }


    @Test
    public void testThatUserGetsSavedInThePatientRepositoryIfRoleIsPatient() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setAge(20);
        registerUserRequest.setAddress("3, Ebute Olowo Street, Okepopo");
        registerUserRequest.setPhone("123456789");
        registerUserRequest.setGender(UserGender.MALE);
        registerUserRequest.setEmail("pee@gmail.com");
        registerUserRequest.setPassword("ola");
        registerUserRequest.setLastName("Olamide");
        registerUserRequest.setFirstName("Olamide");
        registerUserRequest.setRole(UserRoles.PATIENT);
        RegisterUserResponse response = userService.registerUser(registerUserRequest);

        assertTrue(patientRepository.existsByEmail(registerUserRequest.getEmail()));
    }

    @Test
    public void testThatUserRegistrationThrowsAnExceptionIfYouRegisterWithTheSameEmail() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setAge(20);
        registerUserRequest.setAddress("3, Ebute Olowo Street, Allen");
        registerUserRequest.setPhone("123456789");
        registerUserRequest.setGender(UserGender.MALE);
        registerUserRequest.setEmail("aladeamidatayomide@gmail.com");
        registerUserRequest.setPassword("password");
        registerUserRequest.setLastName("Olamide");
        registerUserRequest.setFirstName("Olamide");

        assertThrows(EmailAlreadyExistException.class, () -> {
            userService.registerUser(registerUserRequest);
        });
    }

    @Test
    public void testThatUserRegistrationThrowsAnExceptionIfEmailIsNull() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setAge(20);
        registerUserRequest.setAddress("3, Ebute Olowo Street, Okepopo");
        registerUserRequest.setPhone("123456789");
        registerUserRequest.setGender(UserGender.MALE);
        registerUserRequest.setEmail("");
        registerUserRequest.setPassword("reddit");
        registerUserRequest.setLastName("Olamide");
        registerUserRequest.setFirstName("Olamide");

        assertThrows(EmptyDetailsException.class, () -> {
            userService.registerUser(registerUserRequest);
        });
    }

    @Test
    public void testThatUserRegistrationThrowsAnExceptionIfPasswordIsNull() {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setAge(20);
        registerUserRequest.setAddress("3, Ebute Olowo Street, Okepopo");
        registerUserRequest.setPhone("123456789");
        registerUserRequest.setGender(UserGender.MALE);
        registerUserRequest.setEmail("Mastr@gmail.com");
        registerUserRequest.setPassword("");
        registerUserRequest.setLastName("Olamide");
        registerUserRequest.setFirstName("Olamide");

        assertThrows(EmptyDetailsException.class, () -> {
            userService.registerUser(registerUserRequest);
        });
    }

    @Test
    public void testThatUsersCanLoginWithCorrectDetails(){
        UserLoginRequest login = new UserLoginRequest();
        login.setEmail("aladeamidatayomide@gmail.com");
        login.setPassword("password");
        UserLoginResponse response = userService.logUserIn(login);
        assertEquals("login successful!", response.getMessage());
    }

    @Test
    public void testThatUserLoginThrowsAnExceptionIfYouTryToLoginWithEmptyDetails(){
        UserLoginRequest login = new UserLoginRequest();
        login.setEmail("");
        login.setPassword("");

        assertThrows(EmptyDetailsException.class, () -> {
            userService.logUserIn(login);
        });
    }

    @Test
    public void testThatUserRegistrationThrowsAnExceptionIfUserIsNotFound(){
        UserLoginRequest login = new UserLoginRequest();
        login.setEmail("ola@gmail.com");
        login.setPassword("password");

        assertThrows(UserNotFound.class, () -> {
            userService.logUserIn(login);
        });
    }

    @Test
    public void testThatUserCanRegisterAsAnAdminAndNeedsTheSuperAdminToVerifyBeforeBeingAllowedIntoTheApp(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setAge(45);
        registerUserRequest.setRole(UserRoles.ADMIN);
        registerUserRequest.setPassword("passworD");
        registerUserRequest.setFirstName("Feyi");
        registerUserRequest.setLastName("Meyi");
        registerUserRequest.setGender(UserGender.MALE);
        registerUserRequest.setPhone("123456789");
        registerUserRequest.setAddress("ayyGWGFIWHDHSUD");
        registerUserRequest.setEmail("olamide@gmail.com");

        RegisterUserResponse registerUserResponse = userService.registerUser(registerUserRequest);
        assertEquals("Registration successful, waiting for approval",  registerUserResponse.getMessage());
    }

    @Test
    public void testThatRegisterUserCanRegisterSuperAdmin(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setAge(20);
        registerUserRequest.setRole(UserRoles.SUPER_ADMIN);
        registerUserRequest.setPassword("tree");
        registerUserRequest.setFirstName("Suli");
        registerUserRequest.setLastName("Oderinde");
        registerUserRequest.setGender(UserGender.FEMALE);
        registerUserRequest.setPhone("08091234657");
        registerUserRequest.setAddress("3, Sango otta, Ogun State");
        registerUserRequest.setEmail("yetuns1122@gmail.com");
        RegisterUserResponse response = userService.registerUser(registerUserRequest);

        assertEquals("Registration successful, superAdmin!", response.getMessage());
        assertTrue(superAdminRepo.existsByEmail("yetuns1122@gmail.com"));
    }

    @Test
    public void testThatThereCanOnlyBeOneSuperAdmin(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setAge(30);
        registerUserRequest.setRole(UserRoles.SUPER_ADMIN);
        registerUserRequest.setPassword("trust");
        registerUserRequest.setFirstName("Suliat");
        registerUserRequest.setLastName("Oderinde");
        registerUserRequest.setGender(UserGender.FEMALE);
        registerUserRequest.setPhone("08091234657");
        registerUserRequest.setAddress("3, Sango otta, Ogun State");
        registerUserRequest.setEmail("wisdom@gmail.com");

        assertThrows(FailedRoleException.class, () ->{
            userService.registerUser(registerUserRequest);
        });
    }

    @Test
    public void testThatAPatientCanLodgeAComplaint(){
        LodgeComplaintRequest request = new LodgeComplaintRequest();
        request.setTitle("Severe headache");
        request.setDescription("Full details");


    }

}