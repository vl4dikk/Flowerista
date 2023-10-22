package ua.flowerista.shop.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.flowerista.shop.dto.BouqueteDto;
import ua.flowerista.shop.mappers.BouqueteMapper;
import ua.flowerista.shop.repo.BouqueteRepository;

@Service
public class BouqueteService {

	private BouqueteRepository repo;
	private BouqueteMapper mapper;

	@Autowired
	public BouqueteService(BouqueteRepository repo, BouqueteMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	public void insert(BouqueteDto dto) {
		repo.save(mapper.toEntity(dto));
	}

	public void deleteById(int id) {
		repo.deleteById(id);
	}

	public List<BouqueteDto> getAllBouquetes() {
		return repo.findAll().stream().map(bouquete -> mapper.toDto(bouquete)).collect(Collectors.toList());
	}

	public BouqueteDto getBouqueteById(int id) {
		return mapper.toDto(repo.getReferenceById(id));
	}

	public void update(BouqueteDto dto) {
		repo.save(mapper.toEntity(dto));
	}

	public List<BouqueteDto> getBouquetesBestSellers() {
		return repo.findTop5ByOrderBySoldQuantityDesc().stream().map(bouquete -> mapper.toDto(bouquete))
				.collect(Collectors.toList());
	}

	public List<BouqueteDto> getBouquetesTop5Sales() {
		return repo.findTop5ByOrderByDiscountDesc().stream().map(bouquete -> mapper.toDto(bouquete))
				.collect(Collectors.toList());
	}

}
