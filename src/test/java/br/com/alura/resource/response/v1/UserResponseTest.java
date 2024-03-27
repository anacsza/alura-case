package br.com.alura.resource.response.v1;

import br.com.alura.model.UserRole;

public final class UserResponseTest {

	public static UserResponse build() {
		return new UserResponse().setEmail("ana@gmail.com").setName("Ana").setRole(UserRole.ADMIN);
	}
}
