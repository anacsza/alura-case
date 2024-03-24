package br.com.alura.resource.response.v1;

import java.io.Serializable;

public class EnrollmentResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private int enrollments;

	private int nps;

	private String code;

	private String name;

	private String description;

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
