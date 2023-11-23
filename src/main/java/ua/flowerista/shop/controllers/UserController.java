package ua.flowerista.shop.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import ua.flowerista.shop.services.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Tag(name="USER controller", description = "Operations that auth`d user can do")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/profile")
	public ResponseEntity<?> profile (@RequestBody Principal connectedUser) {
		return ResponseEntity.ok(service.getUserDto(connectedUser));
	}

}
