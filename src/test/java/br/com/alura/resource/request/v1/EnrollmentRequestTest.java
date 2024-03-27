package br.com.alura.resource.request.v1;

public final class EnrollmentRequestTest {

	public static EnrollmentRequest build() {
		return new EnrollmentRequest().setCode("spring-boot").setUsername("ana");
	}
}
