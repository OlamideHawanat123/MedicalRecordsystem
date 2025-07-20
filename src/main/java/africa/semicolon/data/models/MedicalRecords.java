package africa.semicolon.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "Medical Record")
public class MedicalRecords {
    @Id
    private String id;
    private String patientId;
    private String doctorId;

    private String diagnosis;
    private String symptoms;
    private String treatment;
    private String notes;
    private List<String> fileUrls;
    private LocalDate visitDate;
    private String visitType;
    private LocalDate nextAppointmentDate;

}
