package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
    boolean existsByEmail(String email);
    Optional<Admin> findAdminByEmail(String email);

}
