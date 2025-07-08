package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Data
@Document(collection = "Verification codes")
public class VerificationCode {
    @Id
    private String id;
    private String email;
    private String code;

    private Date expiryDate;

    public VerificationCode(String email,String code) {
        this.email = email;
        this.code = code;
        this.expiryDate = Date.from(Instant.now().plusSeconds(300));
    }
}
