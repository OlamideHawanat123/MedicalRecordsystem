package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class Complaint {
    @Id
    private int id;
    private String title;
    private String description;
    private String userId;

    @DBRef
    private User user;
}
