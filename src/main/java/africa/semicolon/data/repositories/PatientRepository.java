package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    boolean existsByEmail(String email);
    Patient findPatientByEmailIgnoreCase(String email);
}
