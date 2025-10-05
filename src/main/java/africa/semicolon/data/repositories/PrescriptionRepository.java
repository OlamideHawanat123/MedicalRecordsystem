package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrescriptionRepository extends MongoRepository<Prescription, String> {
}
