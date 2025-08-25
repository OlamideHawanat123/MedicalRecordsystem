package africa.semicolon.controllers;

import africa.semicolon.Exceptions.UserNotFound;
import africa.semicolon.Services.SuperAdminService;
import africa.semicolon.dtos.requests.RemoveAdminRequest;
import africa.semicolon.dtos.responses.RemoveAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/superAdmin/")
public class SuperAdminServiceController {
    @Autowired
    private SuperAdminService superAdminService;

    @DeleteMapping
    public ResponseEntity<?> deleteAdmin(@RequestBody RemoveAdminRequest removeAdminRequest){
        try {
            RemoveAdminResponse response = superAdminService.removeAdmin(removeAdminRequest);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(UserNotFound e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
