package africa.semicolon.controller;


import africa.semicolon.Exceptions.UnauthorizedUserException;
import africa.semicolon.JWT.JwtUtils;
import africa.semicolon.Services.CustomUserDetailsService;
import africa.semicolon.dtos.requests.UserLoginRequest;
import africa.semicolon.dtos.responses.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException | UnauthorizedUserException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String token = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new UserLoginResponse("Login successful!", token));
    }
}

