package br.com.alura.resource.response;

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

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}
