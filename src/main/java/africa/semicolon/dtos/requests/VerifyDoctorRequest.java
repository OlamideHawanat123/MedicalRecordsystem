package africa.semicolon.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyDoctorRequest {
    private String licenseId;
    private String email;
}
