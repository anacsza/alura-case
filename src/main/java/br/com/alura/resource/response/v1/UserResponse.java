package br.com.alura.resource.response.v1;

import java.io.Serializable;

import br.com.alura.model.UserRole;

public class UserResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String email;

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
