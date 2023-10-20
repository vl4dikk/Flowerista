package ua.flowerista.shop.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.flowerista.shop.dto.BouqueteDto;
import ua.flowerista.shop.models.Bouquete;
import ua.flowerista.shop.models.Size;

@Component
public class BouqueteMapper implements EntityMapper<Bouquete, BouqueteDto> {

	private ColorMapper colorMapper;
	private FlowerMapper flowerMapper;

	@Autowired
	public BouqueteMapper(ColorMapper colorMapper, FlowerMapper flowerMapper) {
		this.colorMapper = colorMapper;
		this.flowerMapper = flowerMapper;
	}

	@Override
	public Bouquete toEntity(BouqueteDto dto) {
		Bouquete entity = new Bouquete();

		entity.setId(dto.getId());
		entity.setDefaultPrice(dto.getDefaultPrice());
		entity.setDiscount(dto.getDiscount());
		entity.setDiscountPrice(dto.getDiscountPrice());
		entity.setFlowers(dto.getFlowers().stream().map(flowerDto -> flowerMapper.toEntity(flowerDto))
				.collect(Collectors.toSet()));
		entity.setColors(
				dto.getColors().stream().map(colorDto -> colorMapper.toEntity(colorDto)).collect(Collectors.toSet()));
		entity.setItemCode(dto.getItemCode());
		entity.setName(dto.getName());
		entity.setQuantity(dto.getQuantity());
		entity.setSize(Size.valueOf(dto.getSize().toUpperCase().trim()));
		entity.setSoldQuantity(dto.getSoldQuantity());
		return entity;
	}

	@Override
	public BouqueteDto toDto(Bouquete entity) {
		BouqueteDto dto = new BouqueteDto();
		dto.setId(entity.getId());
		dto.setDefaultPrice(entity.getDefaultPrice());
		dto.setDiscount(entity.getDiscount());
		dto.setDiscountPrice(entity.getDiscountPrice());
		dto.setFlowers(
				entity.getFlowers().stream().map(flower -> flowerMapper.toDto(flower)).collect(Collectors.toSet()));
		dto.setColors(entity.getColors().stream().map(color -> colorMapper.toDto(color)).collect(Collectors.toSet()));
		dto.setItemCode(entity.getItemCode());
		dto.setName(entity.getName());
		dto.setQuantity(entity.getQuantity());
		dto.setSize(entity.getSize().toString());
		dto.setSoldQuantity(entity.getSoldQuantity());
		return dto;
	}

}
