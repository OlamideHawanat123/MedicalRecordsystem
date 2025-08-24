package africa.semicolon.Services;

import africa.semicolon.Exceptions.UserNotFound;
import africa.semicolon.data.models.Admin;
import africa.semicolon.data.repositories.AdminRepository;
import africa.semicolon.dtos.requests.RemoveAdminRequest;
import africa.semicolon.dtos.responses.RemoveAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuperAdminServiceImplementation implements SuperAdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmailService emailService;
    @Override
    public RemoveAdminResponse removeAdmin(RemoveAdminRequest removeAdminRequest) {
        Optional<Admin> adminToBeRemoved = adminRepository.findAdminByEmail(removeAdminRequest.getEmail());
        adminToBeRemoved.ifPresent(admin -> adminRepository.delete(admin));
        RemoveAdminResponse removeAdminResponse = new RemoveAdminResponse();
        removeAdminResponse.setMessage("Admin removed successfully");
        emailService.sendDeletionEmailToAdmin(removeAdminRequest.getEmail());

        RemoveAdminResponse response = new RemoveAdminResponse();
        response.setMessage("Account removed successfully");

        return response;
    }
}
