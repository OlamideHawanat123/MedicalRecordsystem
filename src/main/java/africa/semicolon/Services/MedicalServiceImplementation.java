package africa.semicolon.Services;

import africa.semicolon.Utils.Mapper;
import africa.semicolon.data.models.Complaint;
import africa.semicolon.data.models.ComplaintStatus;
import africa.semicolon.data.models.Prescription;
import africa.semicolon.data.repositories.ComplaintRepository;
import africa.semicolon.data.repositories.DoctorRepository;
import africa.semicolon.data.repositories.PatientRepository;
import africa.semicolon.data.repositories.PrescriptionRepository;
import africa.semicolon.dtos.requests.LodgeComplaintRequest;
import africa.semicolon.dtos.requests.PrescribeDrugRequest;
import africa.semicolon.dtos.responses.LodgeComplaintResponse;
import africa.semicolon.dtos.responses.PrescribeDrugResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class MedicalServiceImplementation implements MedicalService{
    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private PrescriptionRepository prescriptionRepository;



    @Override
    public LodgeComplaintResponse lodgeComplaint(LodgeComplaintRequest request) {
        Complaint complaint = Mapper.mapRequestToComplaint(request);
        complaintRepository.save(complaint);

        LodgeComplaintResponse response = new LodgeComplaintResponse();
        response.setMessage("Complaint lodged successfully");
        response.setComplaintStatus(ComplaintStatus.PENDING);
        response.setCreatedAt(Instant.now());
        return response;
    }

    @Override
    public PrescribeDrugResponse prescribeDrug(PrescribeDrugRequest request) {
        String doctorId = doctorRepo.findDoctorsByEmailIgnoreCase(request.getDoctorEmail()).getId();
        String patientId = patientRepo.findPatientByEmailIgnoreCase(request.getPatientEmail()).getId();

        Prescription prescription = Mapper.mapRequestToPrescription(request);
        prescription.setPatientId(patientId);
        prescription.setDoctorId(doctorId);
        Prescription anotherPrescription = prescriptionRepository.save(prescription);
        return Mapper.mapPrescriptionToResponse(anotherPrescription);

    }
}
