package africa.semicolon.data.repositories;

import africa.semicolon.data.models.VerificationCode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VerificationCodeRepository extends MongoRepository<VerificationCode, String> {
    Optional<VerificationCode> findByEmail(String email);
}
