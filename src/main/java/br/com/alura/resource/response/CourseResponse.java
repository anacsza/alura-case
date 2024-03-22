package br.com.alura.resource.response;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.alura.model.CourseStatus;

public class CourseResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String code;

	private String description;

	private CourseStatus status;

	private LocalDate insertDate;

	private LocalDate inactivateDate;

	private int nps;

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

	public int getNps() {
		return nps;
	}

	public void setNps(int nps) {
		this.nps = nps;
	}
}
