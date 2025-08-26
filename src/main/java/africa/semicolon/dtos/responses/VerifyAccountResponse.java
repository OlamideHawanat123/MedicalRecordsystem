package africa.semicolon.dtos.responses;

import lombok.Data;

@Data
public class VerifyAccountResponse {
    private String email;
    private String message;
}
