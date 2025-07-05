package africa.semicolon.Utils;

import africa.semicolon.data.models.Patient;
import africa.semicolon.data.models.User;
import africa.semicolon.dtos.requests.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Mapper {
    public static User mapRequestToUser(RegisterUserRequest registerUserRequest, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setFirstName(registerUserRequest.getFirstName());
        user.setLastName(registerUserRequest.getLastName());
        user.setGender(registerUserRequest.getGender());
        user.setAddress(registerUserRequest.getAddress());
        user.setAddress(registerUserRequest.getAddress());
        user.setEmail(registerUserRequest.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        user.setGender(registerUserRequest.getGender());
        user.setAge(registerUserRequest.getAge());
        user.setRole(registerUserRequest.getRole());
        return user;
    }

    public static Patient mapRequestToPatient(User user){
        Patient patient = new Patient();
        patient.setFirstName(user.getFirstName());
        patient.setLastName(user.getLastName());
        patient.setGender(user.getGender());
        patient.setAddress(user.getAddress());
        patient.setEmail(user.getEmail().toLowerCase());
        patient.setPassword(user.getPassword());
        patient.setAge(user.getAge());
        patient.setPhoneNumber(user.getPhoneNumber());
        patient.setRole(user.getRole());
        return patient;
    }
}
