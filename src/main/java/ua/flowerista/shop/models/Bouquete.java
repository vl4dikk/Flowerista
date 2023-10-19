package ua.flowerista.shop.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "bouquetes")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Bouquete {

	@Column(name = "bouqueteid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bouqueteId;
	@OneToOne
	@JoinColumn(name = "flower_name", referencedColumnName = "name")
	private Flower flower;
	@Column(name = "itemcode", nullable = true, unique = true)
	@Min(value = 1)
	private String itemCode;
	@Column(name = "name", nullable = true, unique = true)
	@Min(value = 1)
	private String name;
	@Column(name = "defaultprice")
	@NotNull
	private int defaultPrice;
	@Column(name = "discount")
	private int discount;
	@Column(name = "discountprice")
	private int discountPrice;
	@Enumerated
	@Column(name = "size")
	private Size size;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "soldquantity")
	private int soldQuantity;

}
