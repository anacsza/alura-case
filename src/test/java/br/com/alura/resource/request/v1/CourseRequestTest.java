package br.com.alura.resource.request.v1;

public final class CourseRequestTest {

	public static CourseRequest build() {
		return new CourseRequest().setCode("spring-boot").setDescription("Spring Boot").setInstructorUsername("Ana")
				.setName("Spring Boot");
	}
}
