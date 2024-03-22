package br.com.alura.resource.request.v1;

import java.io.Serializable;

import br.com.alura.model.UserRole;

public class UserRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String username;

	private String email;

	private String password;

	private UserRole role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}
