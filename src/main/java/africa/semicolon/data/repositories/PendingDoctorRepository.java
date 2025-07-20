package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Doctors;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendingDoctorRepository extends MongoRepository<Doctors, String> {
    boolean existsByEmail(String email);
}
