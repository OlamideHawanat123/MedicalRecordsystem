package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Complaint;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ComplaintRepository extends MongoRepository<Complaint, String> {
    Optional<Complaint> findComplaintByTitleIgnoreCase(String title);
    Optional<Complaint> findById(String id);
    List<Complaint> findAll();
}
