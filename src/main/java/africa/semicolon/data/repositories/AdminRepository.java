package africa.semicolon.data.repositories;

import africa.semicolon.data.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String> {

}
