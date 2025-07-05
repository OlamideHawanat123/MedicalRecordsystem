package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Doctors;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorsRepository extends MongoRepository<Doctors,String> {
}
