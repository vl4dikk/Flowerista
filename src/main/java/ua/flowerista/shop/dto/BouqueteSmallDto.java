package ua.flowerista.shop.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BouqueteSmallDto {
	private int id;
	private String name;
    private Map<Integer, String> imageUrls;
	private int defaultPrice;
	private Integer discount;
	private Integer discountPrice;
}
