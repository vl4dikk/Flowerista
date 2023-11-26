package ua.flowerista.shop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserPasswordResetDto {

	private String password;

	private String token;

	private String passwordRepeated;
}
