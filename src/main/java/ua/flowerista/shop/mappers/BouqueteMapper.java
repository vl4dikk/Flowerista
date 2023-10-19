package ua.flowerista.shop.mappers;

import org.springframework.stereotype.Component;

import ua.flowerista.shop.dto.BouqueteDto;
import ua.flowerista.shop.models.Bouquete;
import ua.flowerista.shop.models.Flower;
import ua.flowerista.shop.models.Size;

@Component
public class BouqueteMapper implements EntityMapper<Bouquete, BouqueteDto> {

	@Override
	public Bouquete toEntity(BouqueteDto dto) {
		Bouquete entity = new Bouquete();
		Flower flower = new Flower();
		flower.setName(dto.getFlower());

		entity.setBouqueteId(dto.getBouqueteId());
		entity.setDefaultPrice(dto.getDefaultPrice());
		entity.setDiscount(dto.getDiscount());
		entity.setDiscountPrice(dto.getDiscountPrice());
		entity.setFlower(flower);
		entity.setItemCode(dto.getItemCode());
		entity.setName(dto.getName());
		entity.setQuantity(dto.getQuantity());
		entity.setSize(Size.valueOf(dto.getSize()));
		entity.setSoldQuantity(dto.getSoldQuantity());
		return entity;
	}

	@Override
	public BouqueteDto toDto(Bouquete entity) {
		BouqueteDto dto = new BouqueteDto();
		dto.setBouqueteId(entity.getBouqueteId());
		dto.setDefaultPrice(entity.getDefaultPrice());
		dto.setDiscount(entity.getDiscount());
		dto.setDiscountPrice(entity.getDiscountPrice());
		dto.setFlower(entity.getFlower().getName());
		dto.setItemCode(entity.getItemCode());
		dto.setName(entity.getName());
		dto.setQuantity(entity.getQuantity());
		dto.setSize(entity.getSize().toString());
		dto.setSoldQuantity(entity.getSoldQuantity());
		return dto;
	}

}
