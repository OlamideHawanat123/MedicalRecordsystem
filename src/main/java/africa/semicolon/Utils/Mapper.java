package africa.semicolon.Utils;

import africa.semicolon.data.models.*;
import africa.semicolon.dtos.requests.LodgeComplaintRequest;
import africa.semicolon.dtos.requests.RegisterUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

public class Mapper {
    public static User mapRequestToUser(RegisterUserRequest registerUserRequest, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setFirstName(registerUserRequest.getFirstName());
        user.setLastName(registerUserRequest.getLastName());
        user.setGender(registerUserRequest.getGender());
        user.setAddress(registerUserRequest.getAddress());
        user.setEmail(registerUserRequest.getEmail().trim().toLowerCase());
        user.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        user.setGender(registerUserRequest.getGender());
        user.setAge(registerUserRequest.getAge());
        user.setRole(registerUserRequest.getRole());
        return user;
    }

    public static Patient mapUserToPatient(User user){
        Patient patient = new Patient();
        patient.setFirstName(user.getFirstName());
        patient.setLastName(user.getLastName());
        patient.setGender(user.getGender());
        patient.setAddress(user.getAddress());
        patient.setEmail(user.getEmail().trim().toLowerCase());
        patient.setPassword(user.getPassword());
        patient.setAge(user.getAge());
        patient.setPhoneNumber(user.getPhoneNumber());
        patient.setRole(user.getRole());
        return patient;
    }

    public static Doctors mapUserToDoctors(User user){
        Doctors doctors = new Doctors();
        doctors.setFirstName(user.getFirstName());
        doctors.setLastName(user.getLastName());
        doctors.setGender(user.getGender());
        doctors.setAddress(user.getAddress());
        doctors.setEmail(user.getEmail().trim().toLowerCase());
        doctors.setPassword(user.getPassword());
        doctors.setAge(user.getAge());
        doctors.setPhoneNumber(user.getPhoneNumber());
        doctors.setRole(user.getRole());
        doctors.setLicenseId(doctors.getLicenseId());
        doctors.setSpecialization(doctors.getSpecialization());
        return doctors;
    }

    public static Admin mapUsersToAdmin(User user){
        Admin admin = new Admin();
        admin.setFirstName(user.getFirstName());
        admin.setLastName(user.getLastName());
        admin.setGender(user.getGender());
        admin.setAddress(user.getAddress());
        admin.setEmail(user.getEmail().trim().toLowerCase());
        admin.setPassword(user.getPassword());
        admin.setAge(user.getAge());
        admin.setPhoneNumber(user.getPhoneNumber());
        admin.setRole(user.getRole());
        return admin;
    }

    public static SuperAdmin mapUsersToSuperAdmin(User user){
        SuperAdmin admin = new SuperAdmin();
        admin.setFirstName(user.getFirstName());
        admin.setLastName(user.getLastName());
        admin.setGender(user.getGender());
        admin.setAddress(user.getAddress());
        admin.setEmail(user.getEmail().trim().toLowerCase());
        admin.setPassword(user.getPassword());
        admin.setAge(user.getAge());
        admin.setPhoneNumber(user.getPhoneNumber());
        admin.setRole(user.getRole());
        return admin;
    }

    public static Complaint mapRequestToComplaint(LodgeComplaintRequest request){
        Complaint complaint = new Complaint();
        complaint.setTitle(request.getTitle());
        complaint.setSeverity(request.getSeverity());
        complaint.setDescription(request.getDescription());
        complaint.setStatus(request.getStatus());
        complaint.setCreatedAt(Instant.now());
        complaint.setStartDate(request.getStartDate());
        return complaint;
    }
}
