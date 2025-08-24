package africa.semicolon.Services;

import africa.semicolon.Exceptions.UserNotFound;
import africa.semicolon.data.models.Doctors;
import africa.semicolon.data.repositories.PendingDoctorRepository;
import africa.semicolon.dtos.requests.VerifyDoctorRequest;
import africa.semicolon.dtos.responses.VerifyDoctorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImplementation implements AdminService{
    @Autowired
    private PendingDoctorRepository pendingDoctorRepository;


    @Autowired
    private EmailService emailService;
    @Override
    public VerifyDoctorResponse verifyDoctor(VerifyDoctorRequest request) {
        Optional<Doctors> doctor = Optional
                .ofNullable(pendingDoctorRepository.findDoctorsByLicenseId(request.getLicenseId())
                        .orElseThrow(() -> new UserNotFound("You need to register first")));

        doctor.get().setLicensedVerified(true);
        pendingDoctorRepository.save(doctor.get());

        VerifyDoctorResponse response = new VerifyDoctorResponse();
        response.setMessage("Check your email for message");
        emailService.sendVerificationToDoctor(doctor.get());
        return response;

    }

    @Override
    public VerifyDoctorResponse rejectVerification(VerifyDoctorRequest request) {
        Optional<Doctors> doctor = Optional
                .ofNullable(pendingDoctorRepository.findDoctorsByLicenseId(request.getLicenseId())
                        .orElseThrow(() -> new UserNotFound("You need to register first")));

        pendingDoctorRepository.delete(doctor.get());
        VerifyDoctorResponse response = new VerifyDoctorResponse();
        response.setMessage("Check your email for message");

        emailService.sendRejectionEmailToDoctor(doctor.get());
        return response;
    }
}
