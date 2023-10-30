package ua.flowerista.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import ua.flowerista.shop.dto.ColorDto;
import ua.flowerista.shop.services.ColorService;

@RestController
@RequestMapping("/api/color")
@CrossOrigin
public class ColorController {
	
	@Autowired
	ColorService service;
	
	@GetMapping
	@CrossOrigin
	@Operation(summary = "Get all collors", description = "Returns list of all colors")
	public ResponseEntity<List<ColorDto>> getAllCollors() {
		List<ColorDto> colors = service.getAllColors();
		return ResponseEntity.ok(colors);
	}

}
