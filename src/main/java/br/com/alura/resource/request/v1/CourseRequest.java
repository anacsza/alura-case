package br.com.alura.resource.request.v1;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;

public class CourseRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+(?:-[a-zA-Z]+){0,9}$", flags = Flag.CASE_INSENSITIVE)
	private String code;

	@NotNull
	@NotBlank
	@Pattern(regexp = "[a-z]{1,20}$", flags = Flag.CASE_INSENSITIVE)
	private String instructorUsername;

	private String description;

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

	public String getInstructorUsername() {
		return instructorUsername;
	}

	public void setInstructorUsername(String instructorUsername) {
		this.instructorUsername = instructorUsername;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CourseRequest [name=" + name + ", code=" + code + ", instructorUsername=" + instructorUsername
				+ ", description=" + description + "]";
	}
}
