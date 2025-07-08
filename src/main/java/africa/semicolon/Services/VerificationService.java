package africa.semicolon.Services;

import africa.semicolon.Exceptions.FailedVerificationException;
import africa.semicolon.data.models.VerificationCode;
import africa.semicolon.data.repositories.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationService {
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    private EmailService emailService;

    public void sendVerification(String email){
        String code =emailService.generateVerificationCode();
        VerificationCode verificationCode = new VerificationCode(email, code);
        verificationCodeRepository.save(verificationCode);
        emailService.sendVerificationEmail(email, code);
    }

    public boolean verifyConfirmationCode(String email, String code){
        Optional<VerificationCode> optional = verificationCodeRepository.findByEmail(email);

        if(optional.isEmpty()) throw new FailedVerificationException("Failed to verify!");
        VerificationCode verificationCode = optional.get();

        if(! verificationCode.getCode().equals(code)) throw new FailedVerificationException("Verification code does not match!");
        verificationCodeRepository.deleteById(verificationCode.getId());
        return true;
    }
}
