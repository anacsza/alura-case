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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
	@NotNull
	private CourseStatus status;

	@Column(name = "insert_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private LocalDateTime insertDate;

	@Column(name = "inactivate_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private LocalDateTime inactivateDate;

	@OneToOne
	@JoinColumn(referencedColumnName = "id", name = "user_id", nullable = false)
	private User user;

	public Course() {

	}

	public String getName() {
		return name;
	}

	public Course setName(String name) {
		this.name = name;
		return this;
	}

	public String getCode() {
		return code;
	}

	public Course setCode(String code) {
		this.code = code;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Course setDescription(String description) {
		this.description = description;
		return this;
	}

	public CourseStatus getStatus() {
		return status;
	}

	public Course setStatus(CourseStatus status) {
		this.status = status;
		return this;
	}

	public LocalDateTime getInsertDate() {
		return insertDate;
	}

	public Course setInsertDate(LocalDateTime insertDate) {
		this.insertDate = insertDate;
		return this;
	}

	public LocalDateTime getInactivateDate() {
		return inactivateDate;
	}

	public Course setInactivateDate(LocalDateTime inactivateDate) {
		this.inactivateDate = inactivateDate;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Course setUser(User user) {
		this.user = user;
		return this;
	}

}
