package br.com.alura.resource.response.v1;

import java.io.Serializable;

import br.com.alura.model.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotBlank
	@Schema(example = "Ana")
	private String name;

	@NotNull
	@NotBlank
	@Schema(example = "ana@gmail.com")
	private String email;

	@NotNull
	@NotBlank
	@Schema(example = "STUDENT")
	private UserRole role;

	public String getName() {
		return name;
	}

	public UserResponse setName(String name) {
		this.name = name;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserResponse setEmail(String email) {
		this.email = email;
		return this;
	}

	public UserRole getRole() {
		return role;
	}

	public UserResponse setRole(UserRole role) {
		this.role = role;
		return this;
	}

}
