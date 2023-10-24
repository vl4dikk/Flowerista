package ua.flowerista.shop.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import ua.flowerista.shop.dto.BouqueteDto;
import ua.flowerista.shop.services.BouqueteService;

@RestController
@RequestMapping("/api/bouquete")
@CrossOrigin(origins = "*")
public class BouqueteController {
	
	@Autowired
	private BouqueteService service;
	
	@GetMapping("/bs")
	@Operation(summary = "Get bestsellers", description = "Returns list (5 units) of bestsellers")
	public ResponseEntity<CollectionModel<EntityModel<BouqueteDto>>> getBouqueteBestSellers() {
		List<EntityModel<BouqueteDto>> bouquetesModels = service.getBouquetesBestSellers().stream().map(bouquete -> EntityModel.of(bouquete)).collect(Collectors.toList());
		CollectionModel<EntityModel<BouqueteDto>> collectionModel = CollectionModel.of(bouquetesModels);
		return ResponseEntity.ok(collectionModel);
	}
	
	@GetMapping("/ts")
	@Operation(summary = "Get topsales", description = "Returns list (5 units) of topsales")
	public ResponseEntity<CollectionModel<EntityModel<BouqueteDto>>> getBouqueteTopSales() {
		List<EntityModel<BouqueteDto>> bouquetesModels = service.getBouquetesTop5Sales().stream().map(bouquete -> EntityModel.of(bouquete)).collect(Collectors.toList());
		CollectionModel<EntityModel<BouqueteDto>> collectionModel = CollectionModel.of(bouquetesModels);
		return ResponseEntity.ok(collectionModel);
	}
	
	@PostMapping("/addImage")
	public ResponseEntity<Void> addImage(@RequestParam("file") List<MultipartFile> images) {
		BouqueteDto dto = service.getBouqueteById(1);
			service.insert(dto, images);
		return ResponseEntity.ok().build();
	}
}
