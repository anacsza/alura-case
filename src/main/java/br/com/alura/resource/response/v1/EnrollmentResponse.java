package br.com.alura.resource.response.v1;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EnrollmentResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotBlank
	@Schema(example = "5")
	private int enrollments;

	@NotNull
	@NotBlank
	@Schema(example = "50")
	private int nps;

	@NotNull
	@NotBlank
	@Schema(example = "spring-boot")
	private String code;

	@NotNull
	@NotBlank
	@Schema(example = "Spring Boot")
	private String name;

	@Schema(example = "Spring Boot")
	private String description;

	@NotNull
	@NotBlank
	@Schema(example = "ACTIVE")
	private String courseStatus;

	public EnrollmentResponse() {

	}

	public int getEnrollments() {
		return enrollments;
	}

	public EnrollmentResponse setEnrollments(int enrollments) {
		this.enrollments = enrollments;
		return this;
	}

	public int getNps() {
		return nps;
	}

	public EnrollmentResponse setNps(int nps) {
		this.nps = nps;
		return this;
	}

	public String getCode() {
		return code;
	}

	public EnrollmentResponse setCode(String code) {
		this.code = code;
		return this;
	}

	public String getName() {
		return name;
	}

	public EnrollmentResponse setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public EnrollmentResponse setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getCourseStatus() {
		return courseStatus;
	}

	public EnrollmentResponse setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
		return this;
	}

}
