package lt.ca.javau9.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lt.ca.javau9.payload.requests.LoginRequest;
import lt.ca.javau9.payload.requests.SignupRequest;
import lt.ca.javau9.payload.responses.JwtResponse;
import lt.ca.javau9.payload.responses.MessageResponse;
import lt.ca.javau9.security.JwtUtils;
import lt.ca.javau9.services.AuthService;



@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	
	AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser (@RequestBody LoginRequest loginRequest){
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);		
	}
	
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		
		
        try {
            MessageResponse response = authService.registerUser(signUpRequest);
            return ResponseEntity.ok(response);
        } catch (ResponseStatusException e) {
        	
            return ResponseEntity.status(e.getStatusCode())
            		.body(new MessageResponse(e.getReason()));
        }
    }
	
	
	
}
