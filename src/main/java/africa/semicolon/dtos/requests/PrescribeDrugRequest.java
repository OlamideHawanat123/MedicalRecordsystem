package africa.semicolon.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PrescribeDrugRequest {
    private List<String> DrugName;
    private String note;
    private String patientEmail;
    private String doctorEmail;
}
