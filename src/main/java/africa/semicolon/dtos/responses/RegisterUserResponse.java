package africa.semicolon.dtos.responses;

import lombok.Data;

@Data
public class RegisterUserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String message;
}
