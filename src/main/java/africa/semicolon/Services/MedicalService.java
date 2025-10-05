package africa.semicolon.Services;

import africa.semicolon.dtos.requests.LodgeComplaintRequest;
import africa.semicolon.dtos.requests.PrescribeDrugRequest;
import africa.semicolon.dtos.responses.LodgeComplaintResponse;
import africa.semicolon.dtos.responses.PrescribeDrugResponse;

public interface MedicalService {
    LodgeComplaintResponse lodgeComplaint(LodgeComplaintRequest request);

    PrescribeDrugResponse prescribeDrug(PrescribeDrugRequest request);
}
