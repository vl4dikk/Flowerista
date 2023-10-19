package ua.flowerista.shop.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
@Table(name = "flowers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flower {
	
	@Column(name = "flowerid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flowerId;
	@Column(name = "name", nullable = true, unique = true)
	@Min(value = 1)
	private String name;

}
