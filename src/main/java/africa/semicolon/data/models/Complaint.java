package africa.semicolon.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import java.time.Instant;

@Getter
@Setter
public class Complaint {
    @Id
    private String id;
    private String title;
    private String description;
    private Instant startDate;
    private Severity severity;
    private ComplaintStatus status;
    private Instant createdAt;
}
