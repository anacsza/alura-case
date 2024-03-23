package br.com.alura.resource.request.v1;

import java.io.Serializable;

public class CourseRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String code;

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
