package africa.semicolon.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class PrescribeDrugResponse {
    private String id;
    private String message;
    private List<String> drugName;
    private String patientId;
    private String doctorId;
    private Instant datePrescribed;
}
