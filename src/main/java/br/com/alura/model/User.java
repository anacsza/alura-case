package br.com.alura.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user", indexes = {
        @Index(name = "email", columnList = "email"),
        @Index(name = "username", columnList = "username")
})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	private String username;

	@NotNull
	@NotBlank
	private String email;

	@NotNull
	@NotBlank
	private String password;

	@NotNull
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@NotNull
	@Column(name = "insert_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private LocalDateTime insertDate;

	public User() {

	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public UserRole getRole() {
		return role;
	}

	public User setRole(UserRole role) {
		this.role = role;
		return this;
	}

	public LocalDateTime getInsertDate() {
		return insertDate;
	}

	public User setInsertDate(LocalDateTime insertDate) {
		this.insertDate = insertDate;
		return this;
	}
}
