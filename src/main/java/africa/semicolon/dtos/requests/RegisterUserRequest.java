package africa.semicolon.dtos.requests;

import africa.semicolon.data.models.UserGender;
import africa.semicolon.data.models.UserRoles;
import lombok.Data;

@Data
public class RegisterUserRequest {
    private int age;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private UserGender gender;
    private UserRoles role;
    private String specialization;
    private String licenseId;
    private String phoneNumber;
}
