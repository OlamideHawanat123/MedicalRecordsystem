package africa.semicolon.data.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.Date;

public class VerificationCode {
    @Id
    private String id;
    private String email;
    private String code;

    private Date expiryDate;

    public VerificationCode(String email,String code) {}
}
