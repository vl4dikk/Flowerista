package ua.flowerista.shop.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import ua.flowerista.shop.dto.AddressDto;
import ua.flowerista.shop.dto.user.UserChangePasswordRequestDto;
import ua.flowerista.shop.dto.user.UserChangePersonalInfoDto;
import ua.flowerista.shop.services.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Tag(name = "USER controller", description = "Operations that auth`d user can do")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/profile")
	@Operation(summary = "Get user profile dto", description = "Returns bad request if something went wrong, and accepted if everything fine")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "403",
			description = "If user is not authenticated"),
    		@ApiResponse(responseCode = "200",
			description = "If user is authenticated")
    })
	public ResponseEntity<?> profile(Principal connectedUser) {
		return ResponseEntity.ok(service.getUserDto(connectedUser));
	}

	@Operation(summary = "Change password endpoint", description = "Changing authenticated users passwords")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "403",
			description = "If user is not authenticated"),
    		@ApiResponse(responseCode = "400",
			description = "If something went wrong with exception in body"),
    		@ApiResponse(responseCode = "200",
			description = "If user is authenticated")
    })
	@PatchMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody UserChangePasswordRequestDto request,
			Principal connectedUser) {
		String response = service.changePassword(request, connectedUser);
		if (!response.equals("Password changed")) {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping("/changeAddress")
	@Operation(summary = "Change address endpoint", description = "Changing authenticated users addresses")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "403",
			description = "If not valid data"),
    		@ApiResponse(responseCode = "200",
			description = "If user is authenticated")
    })
	public ResponseEntity<?> changeAddress (@RequestBody @Valid AddressDto address, Principal connectedUser) {
		service.changeAddress(address, connectedUser);
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping("/changePersonalInfo")
	@Operation(summary = "Change user personal info endpoint", description = "Changing authenticated users personal info`s")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "403",
			description = "If not valid data"),
    		@ApiResponse(responseCode = "200",
			description = "If user is authenticated")
    })
	public ResponseEntity<?> changePersonalInfo (@RequestBody @Valid UserChangePersonalInfoDto dto, Principal connectedUser) {
		service.changePersonalInfo(dto, connectedUser);
		return ResponseEntity.ok().build();
	}

}
