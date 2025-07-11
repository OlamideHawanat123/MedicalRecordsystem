package africa.semicolon.data.repositories;

import africa.semicolon.data.models.SuperAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperAdminRepo extends MongoRepository<SuperAdmin, String> {
    boolean existsByEmail(String mail);
}
