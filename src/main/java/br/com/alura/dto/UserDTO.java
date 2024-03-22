package br.com.alura.dto;

import java.time.LocalDate;

import br.com.alura.model.User;
import br.com.alura.model.UserRole;

public class UserDTO {

	private String name;

	private String username;

	private String email;

	private UserRole role;

	private LocalDate insertDate;

	public UserDTO(User user) {
		this.name = user.getName();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.role = user.getRole();
		this.insertDate = user.getInsertDate();
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public UserRole getRole() {
		return role;
	}

	public LocalDate getInsertDate() {
		return insertDate;
	}

}
