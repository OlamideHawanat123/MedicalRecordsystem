package africa.semicolon.Services;

import africa.semicolon.Exceptions.UserNotFound;
import africa.semicolon.data.models.Doctors;
import africa.semicolon.data.repositories.DoctorRepository;
import africa.semicolon.data.repositories.UserRepository;
import africa.semicolon.dtos.requests.RemoveDoctorRequest;
import africa.semicolon.dtos.responses.RemoveDoctorResponse;
import africa.semicolon.dtos.requests.VerifyDoctorRequest;
import africa.semicolon.dtos.responses.VerifyDoctorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImplementation implements AdminService{
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private EmailService emailService;
    @Override
    public VerifyDoctorResponse verifyDoctor(VerifyDoctorRequest request) {
        Optional<Doctors> doctor = Optional
                .ofNullable(doctorRepository.findDoctorsByLicenseId(request.getLicenseId())
                        .orElseThrow(() -> new UserNotFound("You need to register first")));

        doctor.get().setLicensedVerified(true);
        doctorRepository.save(doctor.get());

        VerifyDoctorResponse response = new VerifyDoctorResponse();
        response.setMessage("Check your email for message");
        emailService.sendVerificationToDoctor(doctor.get());
        return response;

    }

    @Override
    public VerifyDoctorResponse rejectVerification(VerifyDoctorRequest request) {
        Optional<Doctors> doctor = Optional
                .ofNullable(doctorRepository.findDoctorsByLicenseId(request.getLicenseId())
                        .orElseThrow(() -> new UserNotFound("You need to register first")));

        doctorRepository.delete(doctor.get());
        userRepository.deleteUserByEmail(request.getEmail());
        VerifyDoctorResponse response = new VerifyDoctorResponse();
        response.setMessage("Check your email for message");

        emailService.sendRejectionEmailToDoctor(doctor.get());
        return response;
    }

    @Override
    public RemoveDoctorResponse removeDoctor(RemoveDoctorRequest request) {
        Optional<Doctors> doctorToBeRemoved = doctorRepository.findDoctorsByEmail(request.getEmail());
        doctorToBeRemoved.ifPresent(doctors -> doctorRepository.delete(doctors));
        userRepository.deleteUserByEmail(request.getEmail());

        emailService.sendAccountDeletionToDoctor(request.getEmail());

        RemoveDoctorResponse response = new RemoveDoctorResponse();
        response.setMessage("Account removed successfully");
        return response;
    }


}
