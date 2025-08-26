package africa.semicolon.Services;

import africa.semicolon.dtos.requests.RemoveDoctorRequest;
import africa.semicolon.dtos.responses.RemoveDoctorResponse;
import africa.semicolon.dtos.requests.VerifyDoctorRequest;
import africa.semicolon.dtos.responses.VerifyDoctorResponse;

public interface AdminService {
    VerifyDoctorResponse verifyDoctor(VerifyDoctorRequest request);
    VerifyDoctorResponse rejectVerification(VerifyDoctorRequest request);


    RemoveDoctorResponse removeDoctor(RemoveDoctorRequest request);
}
