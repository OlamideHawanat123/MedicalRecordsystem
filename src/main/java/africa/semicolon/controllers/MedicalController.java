package africa.semicolon.controllers;

import africa.semicolon.data.models.Doctors;
import africa.semicolon.data.repositories.PendingDoctorRepository;
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
    private PendingDoctorRepository pendingDoctorRepository;

    @GetMapping("/pendingDoctors")
    public ResponseEntity<?> getPendingDoctors (){
        try {
            List<Doctors> pendingDoctors = pendingDoctorRepository.findAll();
            return ResponseEntity.status(HttpStatus.FOUND).body(pendingDoctors);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
