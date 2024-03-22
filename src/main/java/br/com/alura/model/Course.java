package br.com.alura.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "course")
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String code;

	private String description;

	@Enumerated(EnumType.STRING)
	@NotBlank
	private CourseStatus status;

	@Column(name = "insert_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private LocalDate insertDate;

	@Column(name = "inactivate_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private LocalDate inactivateDate;

	@OneToOne
	@JoinColumn(referencedColumnName = "id", name = "user_id", nullable = false)
	private User user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CourseStatus getStatus() {
		return status;
	}

	public void setStatus(CourseStatus status) {
		this.status = status;
	}

	public LocalDate getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(LocalDate insertDate) {
		this.insertDate = insertDate;
	}

	public LocalDate getInactivateDate() {
		return inactivateDate;
	}

	public void setInactivateDate(LocalDate inactivateDate) {
		this.inactivateDate = inactivateDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
