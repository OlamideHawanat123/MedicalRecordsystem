package africa.semicolon.controllers;

import africa.semicolon.data.models.Doctors;
import africa.semicolon.data.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medical")
public class MedicalController {
    @Autowired
    private DoctorRepository doctorRepository;

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
}
