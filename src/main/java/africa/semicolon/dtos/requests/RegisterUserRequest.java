package africa.semicolon.dtos.requests;

import africa.semicolon.data.models.UserGender;
import lombok.Data;

@Data
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phone;
    private UserGender gender;
    private int age;
}
