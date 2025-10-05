package africa.semicolon.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class Prescription {
    @Id
    private String id;
    private List<String> drugName;
    private String PatientId;
    private String doctorId;
    private String notes;
    private Instant datePrescribed;
}
