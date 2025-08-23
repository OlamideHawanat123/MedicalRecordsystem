package africa.semicolon.dtos.responses;

import africa.semicolon.data.models.ComplaintStatus;
import lombok.Data;

import java.time.Instant;

@Data
public class LodgeComplaintResponse {
    private String message;
    private ComplaintStatus complaintStatus;
    private Instant createdAt;

}
