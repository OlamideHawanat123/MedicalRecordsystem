package africa.semicolon.dtos.requests;

import lombok.Data;

@Data
public class ConfirmRegistrationCodeRequest {
    private String code;
    private String email;
}
