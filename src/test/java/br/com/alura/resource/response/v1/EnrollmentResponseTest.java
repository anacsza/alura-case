package br.com.alura.resource.response.v1;

import br.com.alura.model.CourseStatus;

public final class EnrollmentResponseTest {

	public static EnrollmentResponse build() {
		return new EnrollmentResponse().setCode("spring-boot").setCourseStatus(CourseStatus.ACTIVE.name())
				.setDescription("Spring Boot").setEnrollments(4).setName("Spring Boot").setNps(50);
	}
}
