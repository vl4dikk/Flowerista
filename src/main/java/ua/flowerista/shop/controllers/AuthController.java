package ua.flowerista.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import ua.flowerista.shop.dto.UserRegistrationBodyDto;
import ua.flowerista.shop.models.User;
import ua.flowerista.shop.registration.OnRegistrationCompleteEvent;
import ua.flowerista.shop.services.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@Tag(name="AUTH controller", description = "Operations with sign up and sign in")
public class AuthController {

	@Autowired
	private UserService service;
	
    @Autowired
    private ApplicationEventPublisher eventPublisher;

	@PostMapping(value = "/register", consumes = "application/json")
	@Operation(summary = "Register new user", description = "Returns bad request if something went wrong, and accepted if everything fine")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "400",
			description = "If email or phone number already exist"),
    		@ApiResponse(responseCode = "202",
			description = "Data was accepted")
    })
	public ResponseEntity<?> registerNewUser(@RequestBody @Valid UserRegistrationBodyDto regDto, final HttpServletRequest request) {
		if(service.existsByEmail(regDto.getEmail())) {
			return ResponseEntity.badRequest().body("Email already exists");
		}if(service.existsByPhoneNumber(regDto.getPhoneNumber())) {
			return ResponseEntity.badRequest().body("Phone number already exists");
		}
		final User registered = service.registerNewUserAccount(regDto);
		eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
		return ResponseEntity.accepted().build();
	}
	
	@GetMapping("/checkEmail/{email}")
	@Operation(summary = "Check if email exists", description = "Returns true - if exists, false - if not")
	public ResponseEntity<Boolean> existsByEmail (@PathVariable (value = "email") String email){
		boolean exists = service.existsByEmail(email);
		if(exists == true) {
			return ResponseEntity.ok(exists);
		}
		return ResponseEntity.ok(exists);
	}
	
	@GetMapping("/checkPhone/{phoneNumber}")
	@Operation(summary = "Check if phone number exists", description = "Returns true - if exists, false - if not")
	public ResponseEntity<Boolean> existsByPhoneNumber (@PathVariable (value = "phoneNumber") int phoneNumber){
		boolean exists = service.existsByPhoneNumber(phoneNumber);
		if(exists == true) {
			return ResponseEntity.ok(exists);
		}
		return ResponseEntity.ok(exists);
	}
	
	@PostMapping("/registrationConfirm/{token}")
	@Operation(summary = "Validating token", description = "If token is expired, deleting token and user")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "400",
			description = "If token is expired or invalid"),
    		@ApiResponse(responseCode = "202",
			description = "Data was accepted",
			content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "valid")))
    })
	public ResponseEntity<?> registrationConfirm (@PathVariable (value = "token") String token){
		String tokenValidated = service.validateVerificationToken(token);
		if(tokenValidated.equals("invalidToken")) {
			return ResponseEntity.badRequest().body(tokenValidated);
		}if(tokenValidated.equals("expired")) {
			return ResponseEntity.badRequest().body(tokenValidated);
		}
     return ResponseEntity.accepted().body(tokenValidated);
	}
	
    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
