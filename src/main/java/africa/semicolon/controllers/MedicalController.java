package africa.semicolon.controllers;

import africa.semicolon.Services.MedicalService;
import africa.semicolon.data.models.Doctors;
import africa.semicolon.data.repositories.DoctorRepository;
import africa.semicolon.dtos.requests.LodgeComplaintRequest;
import africa.semicolon.dtos.requests.PrescribeDrugRequest;
import africa.semicolon.dtos.responses.LodgeComplaintResponse;
import africa.semicolon.dtos.responses.PrescribeDrugResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical/")
public class MedicalController {
  @Autowired
    private MedicalService medicalService;

  @PostMapping("lodgeComplaint")
  public ResponseEntity<?> lodgeComplaint(@RequestBody LodgeComplaintRequest request){
      LodgeComplaintResponse response = medicalService.lodgeComplaint(request);
      return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping("prescribeDrug")
    public ResponseEntity<?> prescribeDrug(@RequestBody PrescribeDrugRequest request){
      PrescribeDrugResponse response = medicalService.prescribeDrug(request);
      return ResponseEntity.status(HttpStatus.OK).body(response);
  }


}
