package africa.semicolon.dtos.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LodgeComplaintRequest {
    private String title;
    private String description;
    private LocalDateTime dateAndTime = LocalDateTime.now();
}
