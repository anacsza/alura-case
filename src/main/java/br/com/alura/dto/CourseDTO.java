package br.com.alura.dto;

import java.time.LocalDate;

import br.com.alura.model.Course;
import br.com.alura.model.CourseStatus;

public class CourseDTO {

	private String name;

	private String code;

	private String description;

	private CourseStatus status;

	private LocalDate insertDate;

	private LocalDate inactivateDate;

	public CourseDTO(Course course) {
		this.name = course.getName();
		this.code = course.getCode();
		this.description = course.getDescription();
		this.status = course.getStatus();
		this.insertDate = course.getInsertDate();
		this.inactivateDate = course.getInactivateDate();
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public CourseStatus getStatus() {
		return status;
	}

	public LocalDate getInsertDate() {
		return insertDate;
	}

	public LocalDate getInactivateDate() {
		return inactivateDate;
	}

}
