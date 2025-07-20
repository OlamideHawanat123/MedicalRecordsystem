package africa.semicolon.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "Patients")
public class Patient extends User{
    private Double weight;
    private Height height;

    @DBRef
    private List<MedicalRecords> medicalRecords;
}
