package br.com.alura.resource.request.v1;

public final class EnrollmentScoreRequestTest {

	public static EnrollmentScoreRequest build() {
		EnrollmentScoreRequest enrollment = new EnrollmentScoreRequest().setScore(10)
				.setScoreDescription("Spring Boot");
		enrollment.setCode("spring-boot");
		enrollment.setUsername("ana");
		return enrollment;
	}
}
