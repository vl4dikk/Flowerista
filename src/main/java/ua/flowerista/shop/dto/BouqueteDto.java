package ua.flowerista.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BouqueteDto {

	private int bouqueteId;
	private String flower;
	private String itemCode;
	private String name;
	private int defaultPrice;
	private int discount;
	private int discountPrice;
	private String size;
	private int quantity;
	private int soldQuantity;

}
