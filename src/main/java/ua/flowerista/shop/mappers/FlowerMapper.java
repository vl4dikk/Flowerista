package ua.flowerista.shop.mappers;

import org.springframework.stereotype.Component;

import ua.flowerista.shop.dto.FlowerDto;
import ua.flowerista.shop.models.Flower;

@Component
public class FlowerMapper implements EntityMapper<Flower, FlowerDto> {

	@Override
	public Flower toEntity(FlowerDto dto) {
		Flower entity = new Flower();
		entity.setFlowerId(dto.getFlowerId());
		entity.setName(dto.getName());
		return entity;
	}

	@Override
	public FlowerDto toDto(Flower entity) {
		FlowerDto dto = new FlowerDto();
		dto.setFlowerId(entity.getFlowerId());
		dto.setName(entity.getName());
		return dto;
	}

}
