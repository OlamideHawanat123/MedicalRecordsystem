package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class Complaint {
    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private ComplaintStatus complaintStatus;

    @DBRef
    private User user;
}
