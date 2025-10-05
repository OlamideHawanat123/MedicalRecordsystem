package africa.semicolon.Services;

import africa.semicolon.Utils.Mapper;
import africa.semicolon.data.models.Complaint;
import africa.semicolon.data.models.ComplaintStatus;
import africa.semicolon.data.models.Doctors;
import africa.semicolon.data.models.Prescription;
import africa.semicolon.data.repositories.ComplaintRepository;
import africa.semicolon.data.repositories.DoctorRepository;
import africa.semicolon.data.repositories.PatientRepository;
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
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private DrugRepository drugRepo;


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
        Prescription prescription = new Prescription();
        prescription.setDatePrescribed(Instant.now());
        prescription.setNote(request.getNote());
        prescription.setDoctorName(request.getDoctorName());
        prescription.setPatientName(request.getPatientName());

        PrescribeDrugResponse theResponse = drugRepo.save(prescription);

        String doctorId = doctorRepo.findDoctorsByFirstNameIgnoreCase(request.getDoctorName()).getId();
        String patientId = patientRepo.findPatientByNameIgnoreCase(request.getPatientName()).getId();

        PrescribeDrugResponse response = new PrescribeDrugResponse();
        response.setId(prescription.getId());
        response.setDrugName(prescription.getDrugName());
        response.setDoctorId(doctorId);
        response.setPatientId(patientId);
        response.setDatePrescribed(prescription.getDatePrescribed());
        response.setMessage("Drug prescription successfully done");
        return response;

    }
}
