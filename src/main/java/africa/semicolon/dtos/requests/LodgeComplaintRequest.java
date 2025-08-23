package africa.semicolon.dtos.requests;

import africa.semicolon.data.models.ComplaintStatus;
import africa.semicolon.data.models.Severity;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class LodgeComplaintRequest {
    private String title;
    private String description;
    private Instant startDate;
    private Severity severity;
    private ComplaintStatus status;
    private Instant createdAt;
}
