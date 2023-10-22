package ua.flowerista.shop.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "bouquete")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Bouquete {

	@Column(name = "id")
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(name = "bouquete_flower", joinColumns = @JoinColumn(name = "bouquete_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "flower_id"))
	private Set<Flower> flowers;
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinTable(name = "bouquete_color", joinColumns = @JoinColumn(name = "bouquete_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "color_id"))
	private Set<Color> colors;
	@Column(name = "itemcode", nullable = false, unique = true)
	@NotBlank
	private String itemCode;
	@Column(name = "name", nullable = false, unique = true)
	@NotBlank
	private String name;
	@Column(name = "defaultprice")
	@NotNull
	private int defaultPrice;
	@Column(name = "discount")
	private Integer discount;
	@Column(name = "discountprice")
	private Integer discountPrice;
	@Enumerated(EnumType.STRING)
	@Column(name = "size", nullable = true)
	private Size size;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "soldquantity")
	private int soldQuantity;

}