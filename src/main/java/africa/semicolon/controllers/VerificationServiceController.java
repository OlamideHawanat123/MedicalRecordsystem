package africa.semicolon.controllers;

import africa.semicolon.Exceptions.UserNotFound;
import africa.semicolon.Services.VerificationService;
import africa.semicolon.dtos.requests.VerifyAccountRequest;
import africa.semicolon.dtos.responses.VerifyAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/verificaation")
public class VerificationServiceController {
    @Autowired
    private VerificationService verificationService;

    @PostMapping("/requestVerification")
    public ResponseEntity<?> requestVerification(@RequestBody VerifyAccountRequest request){
        try {
            VerifyAccountResponse response = verificationService.sendVerification(request.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(UserNotFound e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
