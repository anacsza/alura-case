package br.com.alura.helper.enrollment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import br.com.alura.model.Course;
import br.com.alura.model.Enrollment;
import br.com.alura.model.User;
import br.com.alura.resource.request.v1.EnrollmentRequest;
import br.com.alura.resource.request.v1.EnrollmentScoreRequest;
import br.com.alura.resource.response.BaseErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Component
public class EnrollmentHelper {

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();

	public Enrollment createEnrollment(Course course, User user) {
		return new Enrollment().setCourse(course).setUser(user).setEnrollmentDate(LocalDateTime.now(ZoneId.of("UTC")));
	}

	public void validateEnrollmentRequest(EnrollmentRequest enrollmentRequest) {
		Set<ConstraintViolation<EnrollmentRequest>> violations = validator.validate(enrollmentRequest);
		System.out.println(violations.toString());
		if (!violations.isEmpty()) {
			BaseErrorResponse baseErrorResponse = new BaseErrorResponse().setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMessage("Dados da requisição está inválido.").setDetails(new ArrayList<>());
			for (ConstraintViolation<EnrollmentRequest> violation : violations) {
				baseErrorResponse.getDetails().add(violation.getMessage());
			}
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "");
		}
	}

	public void validateEnrollmentScoreRequest(EnrollmentScoreRequest enrollmentScoreRequest) {
		Set<ConstraintViolation<EnrollmentScoreRequest>> violations = validator.validate(enrollmentScoreRequest);
		System.out.println(violations.toString());
		if (!violations.isEmpty()) {
			BaseErrorResponse baseErrorResponse = new BaseErrorResponse().setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMessage("Dados da requisição está inválido.").setDetails(new ArrayList<>());
			for (ConstraintViolation<EnrollmentScoreRequest> violation : violations) {
				baseErrorResponse.getDetails().add(violation.getMessage());
			}
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "");
		}
	}
}
