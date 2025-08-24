package africa.semicolon.Services;

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


    @Test
    public void testThatAdminCanVerifyDoctors() {
        VerifyDoctorRequest request = new VerifyDoctorRequest();
        request.setLicenseId("012345");
        request.setEmail("belloharyourmidey@gmail.com");

        VerifyDoctorResponse response = adminService.verifyDoctor(request);
        assertEquals("Check your email for message", response.getMessage());
    }
}
