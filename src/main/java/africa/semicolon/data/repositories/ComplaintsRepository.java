package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Complaint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintsRepository extends MongoRepository<Complaint, String> {
    boolean existsByTitle(String title);
}
