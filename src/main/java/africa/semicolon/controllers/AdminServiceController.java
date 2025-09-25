package africa.semicolon.controllers;

import africa.semicolon.Exceptions.UserNotFound;
import africa.semicolon.Services.AdminService;
import africa.semicolon.data.models.Complaint;
import africa.semicolon.data.models.Doctors;
import africa.semicolon.data.repositories.DoctorRepository;
import africa.semicolon.dtos.requests.RemoveDoctorRequest;
import africa.semicolon.dtos.responses.RemoveDoctorResponse;
import africa.semicolon.dtos.requests.VerifyDoctorRequest;
import africa.semicolon.dtos.responses.VerifyDoctorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminServiceController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private DoctorRepository doctorRepository;

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

    @GetMapping("/pendingDoctors")
    public ResponseEntity<?> getUnverifiedDoctors (){
        try {
            List<Doctors> pendingDoctors = doctorRepository.findDoctorsByIsLicensedVerifiedFalse();
            return ResponseEntity.status(HttpStatus.OK).body(pendingDoctors);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/availableDoctors")
    public ResponseEntity<?> getAvailableDoctors(){
        try{
            List<Doctors> availableDoctors = doctorRepository.findDoctorsByIsAvailableTrue();
            return ResponseEntity.status(HttpStatus.OK).body(availableDoctors);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

        @PutMapping("/complaints/{complaintId}/assign/{doctorId}")
        public ResponseEntity<Complaint> assignDoctor(
                @PathVariable String complaintId,
                @PathVariable String doctorId) {

            Complaint updatedComplaint = adminService.assignDoctor(complaintId, doctorId);
            return ResponseEntity.ok(updatedComplaint);
        }
}
