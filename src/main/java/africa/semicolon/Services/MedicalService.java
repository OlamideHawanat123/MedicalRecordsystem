package africa.semicolon.Services;

import africa.semicolon.dtos.requests.LodgeComplaintRequest;
import africa.semicolon.dtos.responses.LodgeComplaintResponse;

public interface MedicalService {
    LodgeComplaintResponse lodgeComplaint(LodgeComplaintRequest request);
}
