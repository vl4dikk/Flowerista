package ua.flowerista.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import ua.flowerista.shop.dto.BouqueteSmallDto;
import ua.flowerista.shop.dto.PriceRangeDto;
import ua.flowerista.shop.services.BouqueteService;

@RestController
@RequestMapping("/api/bouquete")
@CrossOrigin(origins = "*")
public class BouqueteController {
	
	@Autowired
	private BouqueteService service;
	
	@GetMapping("/bs")
	@Operation(summary = "Get bestsellers", description = "Returns list (5 units) of bestsellers")
	public ResponseEntity<List<BouqueteSmallDto>> getBouqueteBestSellers() {
		List<BouqueteSmallDto> bouquetesModels = service.getBouquetesBestSellers();
		return ResponseEntity.ok(bouquetesModels);
	}
	
	@GetMapping("/ts")
	@Operation(summary = "Get topsales", description = "Returns list (5 units) of topsales")
	public ResponseEntity<List<BouqueteSmallDto>> getBouqueteTopSales() {
		List<BouqueteSmallDto> bouquetesModels = service.getBouquetesTop5Sales();
		return ResponseEntity.ok(bouquetesModels);
	}
	
	@GetMapping
	@Operation(summary = "Get catalog with filters", description = "Returns page (20 units) of bouquetes filtered and sorted")
	public ResponseEntity<Page<BouqueteSmallDto>> getBouqueteCatalog(@RequestParam(required = false) List<Integer> flowerIds,
            @RequestParam(required = false) List<Integer> colorIds,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(defaultValue = "false") Boolean sortByNewest,
            @RequestParam(defaultValue = "false") Boolean sortByPriceHighToLow,
            @RequestParam(defaultValue = "false") Boolean sortByPriceLowToHigh,
            @RequestParam(defaultValue = "1")int page){
		
		return ResponseEntity.ok(service.getBouquetesCatalogFiltered(flowerIds, colorIds, minPrice, maxPrice, sortByNewest, sortByPriceHighToLow, sortByPriceLowToHigh, page));
		
	}
	
    @GetMapping("/price-range")
    @Operation(summary = "Get price range of bouquetes", description = "Returns 2 Integers with min and max price")
    public ResponseEntity<PriceRangeDto> getPriceRange() {
        PriceRangeDto priceRange = service.getMinMaxPrices();
        return ResponseEntity.ok(priceRange);
    }

}
