package africa.semicolon.Services;

import africa.semicolon.dtos.requests.LodgeComplaintRequest;
import africa.semicolon.dtos.responses.LodgeComplaintResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MedicalServiceImplementationTest {

    @Autowired
    private MedicalService medicalService;

    @Test
    public void testThatAPatientCanLodgeAComplaint(){
        LodgeComplaintRequest request = new LodgeComplaintRequest();
        request.setTitle("Severe headache");
        request.setDescription("Full details");

        LodgeComplaintResponse response = medicalService.lodgeComplaint(request);
        assertNotNull(response);
        assertEquals("Complaint lodged successfully", response.getMessage());
    }
}
