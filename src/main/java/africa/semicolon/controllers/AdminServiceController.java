package africa.semicolon.controllers;

import africa.semicolon.Exceptions.UserNotFound;
import africa.semicolon.Services.AdminService;
import africa.semicolon.dtos.requests.RemoveDoctorRequest;
import africa.semicolon.dtos.responses.RemoveDoctorResponse;
import africa.semicolon.dtos.requests.VerifyDoctorRequest;
import africa.semicolon.dtos.responses.VerifyDoctorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminServiceController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/verifyDoctor")
    public ResponseEntity<?> verifyDoctor(@RequestBody VerifyDoctorRequest verifyDoctorRequest){
        try {
            VerifyDoctorResponse response = adminService.verifyDoctor(verifyDoctorRequest);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }catch(UserNotFound e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/removeDoctor")
    public ResponseEntity<?> removeDoctor(@RequestBody RemoveDoctorRequest request){
        try {
            RemoveDoctorResponse response = adminService.removeDoctor(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(UserNotFound e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
