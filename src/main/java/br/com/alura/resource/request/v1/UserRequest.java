package br.com.alura.resource.request.v1;

import java.io.Serializable;

import br.com.alura.model.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;
import jakarta.validation.constraints.Size;

public class UserRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotBlank
	@Size(max = 100)
	@Schema(example = "Ana")
	private String name;

	@NotNull
	@NotBlank
	@Pattern(regexp = "[a-z]{1,20}$", flags = Flag.CASE_INSENSITIVE)
	@Schema(example = "ana")
	private String username;

	@NotNull
	@NotBlank
	@Email
	@Size(max = 100)
	@Schema(example = "ana@gmail.com")
	private String email;

	@NotNull
	@NotBlank
	@Size(max = 100)
	@Schema(example = "123456789@qwerty")
	private String password;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Schema(example = "STUDENT")
	private UserRole role;

	public String getName() {
		return name;
	}

	public UserRequest setName(String name) {
		this.name = name;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public UserRequest setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserRequest setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserRequest setPassword(String password) {
		this.password = password;
		return this;
	}

	public UserRole getRole() {
		return role;
	}

	public UserRequest setRole(UserRole role) {
		this.role = role;
		return this;
	}

	@Override
	public String toString() {
		return "UserRequest [name=" + name + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", role=" + role + "]";
	}
}
