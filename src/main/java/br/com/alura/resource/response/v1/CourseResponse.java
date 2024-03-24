package br.com.alura.resource.response.v1;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.alura.model.CourseStatus;

public class CourseResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String code;

	private String description;

	private CourseStatus status;

	private LocalDateTime insertDate;

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
