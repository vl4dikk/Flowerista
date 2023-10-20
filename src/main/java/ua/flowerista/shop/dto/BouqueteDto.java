package ua.flowerista.shop.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BouqueteDto {

	private int id;
	private Set<FlowerDto> flowers;
	private Set<ColorDto> colors;
	private String itemCode;
	private String name;
	private int defaultPrice;
	private int discount;
	private int discountPrice;
	private String size;
	private int quantity;
	private int soldQuantity;

}
