package africa.semicolon.Services;

import africa.semicolon.data.repositories.DoctorRepository;
import africa.semicolon.dtos.requests.RemoveDoctorRequest;
import africa.semicolon.dtos.responses.RemoveDoctorResponse;
import africa.semicolon.dtos.requests.VerifyDoctorRequest;
import africa.semicolon.dtos.responses.VerifyDoctorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdminServiceImplementationTest {
    @Autowired
    private AdminService adminService;
    @Autowired
    private DoctorRepository doctorRepository;


    @Test
    public void testThatAdminCanVerifyDoctors() {
        VerifyDoctorRequest request = new VerifyDoctorRequest();
        request.setLicenseId("012345");
        request.setEmail("belloharyourmidey@gmail.com");

        VerifyDoctorResponse response = adminService.verifyDoctor(request);
        assertEquals("Check your email for message", response.getMessage());
    }

    @Test
    public void testThatAdminCanRemoveDoctor(){
        assertTrue(doctorRepository.existsByEmail("belloharyourmidey@gmail.com"));
        RemoveDoctorRequest request = new RemoveDoctorRequest();
        request.setEmail("belloharyourmidey@gmail.com");

        RemoveDoctorResponse response = adminService.removeDoctor(request);
        assertEquals("Account removed successfully", response.getMessage());
        assertFalse(doctorRepository.existsByEmail("belloharyourmidey@gmail.com"));

    }

//    @Test
//    public void testThatAdminCanSelectADoctor(){
//        SelectDoctorRequest request = new SelectDoctorRequest(){
//            request.set
//        }
//    }
}
