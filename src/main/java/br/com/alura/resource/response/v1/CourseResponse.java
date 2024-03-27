package br.com.alura.resource.response.v1;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.alura.model.CourseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotBlank
	@Schema(example = "Spring Boot")
	private String name;

	@NotNull
	@NotBlank
	@Schema(example = "spring-boot")
	private String code;

	@Schema(example = "Spring Boot")
	private String description;

	@NotNull
	@NotBlank
	@Schema(example = "ACTIVE")
	private CourseStatus status;

	@NotNull
	@NotBlank
	@Schema(example = "2024-01-01T00:00:00")
	private LocalDateTime insertDate;

	@NotNull
	@NotBlank
	@Schema(example = "2024-01-01T00:00:00")
	private LocalDateTime inactivateDate;

	public String getName() {
		return name;
	}

	public CourseResponse setName(String name) {
		this.name = name;
		return this;
	}

	public String getCode() {
		return code;
	}

	public CourseResponse setCode(String code) {
		this.code = code;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public CourseResponse setDescription(String description) {
		this.description = description;
		return this;
	}

	public CourseStatus getStatus() {
		return status;
	}

	public CourseResponse setStatus(CourseStatus status) {
		this.status = status;
		return this;
	}

	public LocalDateTime getInsertDate() {
		return insertDate;
	}

	public CourseResponse setInsertDate(LocalDateTime insertDate) {
		this.insertDate = insertDate;
		return this;
	}

	public LocalDateTime getInactivateDate() {
		return inactivateDate;
	}

	public CourseResponse setInactivateDate(LocalDateTime inactivateDate) {
		this.inactivateDate = inactivateDate;
		return this;
	}
}
