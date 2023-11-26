package ua.flowerista.shop.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import ua.flowerista.shop.dto.user.UserChangePasswordRequestDto;
import ua.flowerista.shop.services.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
@Tag(name = "USER controller", description = "Operations that auth`d user can do")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/profile")
	public ResponseEntity<?> profile(Principal connectedUser) {
		return ResponseEntity.ok(service.getUserDto(connectedUser));
	}

	@PatchMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody UserChangePasswordRequestDto request,
			Principal connectedUser) {
		String response = service.changePassword(request, connectedUser);
		if (!response.equals("Password changed")) {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

}
