package africa.semicolon.Services;

import africa.semicolon.data.models.Complaint;
import africa.semicolon.data.models.Doctors;
import africa.semicolon.data.repositories.ComplaintRepository;
import africa.semicolon.data.repositories.DoctorRepository;
import africa.semicolon.dtos.requests.LodgeComplaintRequest;
import africa.semicolon.dtos.responses.LodgeComplaintResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MedicalServiceImplementationTest {

    @Autowired
    private MedicalService medicalService;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ComplaintRepository complaintRepo;

    @Test
    public void testThatAPatientCanLodgeAComplaint(){
        LodgeComplaintRequest request = new LodgeComplaintRequest();
        request.setTitle("Severe headache");
        request.setDescription("Full details");

        LodgeComplaintResponse response = medicalService.lodgeComplaint(request);
        assertNotNull(response);
        assertEquals("Complaint lodged successfully", response.getMessage());
    }

    @Test
    public void testThatUserCanGetTheirComplaintsVisitedByAssigningDoctor() {
        Doctors assignedDoctor = doctorRepo.findDoctorsByEmail("belloharyourmidey@gmail.com")
                .orElseThrow();
        Complaint complaint = complaintRepo.findComplaintByTitleIgnoreCase("Severe Headache")
                .orElseThrow();

        Complaint updatedComplaint = adminService.assignDoctor(complaint.getId(), assignedDoctor.getId());

        Doctors refreshedDoctor = doctorRepo.findById(assignedDoctor.getId()).orElseThrow();
        Complaint refreshedComplaint = complaintRepo.findById(complaint.getId()).orElseThrow();

        assertFalse(refreshedDoctor.isAvailable());
        assertEquals("ASSIGNED", refreshedComplaint.getStatus().name());
        assertEquals("ASSIGNED", updatedComplaint.getStatus().name());
    }

//    @Test
//    public void testThatDoctorCanPrescribeDrugForPatient(){
//        PrescribeDrugRequest request = new PrescribeDrugRequest();
//        request.setDrugName("Flagyl");
//        request.setNote("Sleep well and eat alot of vegetables");
//        request.setName("Florence Florence");
//        request.set
//    }

}
