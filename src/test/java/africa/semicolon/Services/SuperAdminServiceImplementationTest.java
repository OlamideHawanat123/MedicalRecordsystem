package africa.semicolon.Services;

import africa.semicolon.data.repositories.AdminRepository;
import africa.semicolon.dtos.requests.RemoveAdminRequest;
import africa.semicolon.dtos.responses.RemoveAdminResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SuperAdminServiceImplementationTest {
    @Autowired
    private SuperAdminService superAdminService;

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void testThatSuperAdminCanRemoveAnAdmin(){
        assertTrue(adminRepository.existsByEmail("rashid@gmail.com"));
        RemoveAdminRequest removeAdminRequest = new RemoveAdminRequest();
        removeAdminRequest.setEmail("rashid@gmail.com");

        RemoveAdminResponse removeAdminResponse = superAdminService.removeAdmin(removeAdminRequest);
        assertFalse(adminRepository.existsByEmail("rashid@gmail.com"));
    }
}
