package br.com.alura.resource.request.v1;

import br.com.alura.model.UserRole;

public final class UserRequestTest {

	public static UserRequest build() {
		return new UserRequest().setEmail("ana@gmail.com").setName("Ana").setPassword("123456qwerty")
				.setRole(UserRole.ADMIN).setUsername("ana");
	}
}
