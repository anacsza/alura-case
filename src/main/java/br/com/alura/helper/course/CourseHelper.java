package br.com.alura.helper.course;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.model.Course;
import br.com.alura.model.CourseStatus;
import br.com.alura.model.User;
import br.com.alura.model.UserRole;
import br.com.alura.resource.request.v1.CourseRequest;
import br.com.alura.resource.response.BaseErrorResponse;
import br.com.alura.resource.response.v1.CourseResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Component
public class CourseHelper {

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();

	public Page<CourseResponse> createCourseResponse(Page<Course> courses) {
		return courses.map(CourseHelper::createCourseResponse);
	}

	public static CourseResponse createCourseResponse(Course course) {
		return new CourseResponse().setCode(course.getCode()).setDescription(course.getDescription())
				.setName(course.getName()).setInsertDate(course.getInsertDate())
				.setInactivateDate(course.getInactivateDate()).setStatus(course.getStatus());
	}

	public void validateUserRequest(CourseRequest courseRequest) {
		Set<ConstraintViolation<CourseRequest>> violations = validator.validate(courseRequest);
		System.out.println(violations.toString());
		if (!violations.isEmpty()) {
			BaseErrorResponse baseErrorResponse = new BaseErrorResponse().setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMessage("Dados da requisição está inválido.").setDetails(new ArrayList<>());
			for (ConstraintViolation<CourseRequest> violation : violations) {
				baseErrorResponse.getDetails().add(violation.getMessage());
			}
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "");
		}
	}

	public Course createCourse(CourseRequest courseRequest, Optional<User> userInstructor,
			Optional<Course> courseFound) {
		if (userInstructor.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrato");
		}
		if (userInstructor.get().getRole() != UserRole.INSTRUCTOR) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não é instrutor");
		}
		if (courseFound.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código de curso já cadastrado");
		}
		return new Course().setCode(courseRequest.getCode()).setDescription(courseRequest.getDescription())
				.setName(courseRequest.getName()).setInsertDate(LocalDateTime.now(ZoneId.of("UTC")))
				.setStatus(CourseStatus.ACTIVE).setUser(userInstructor.get());
	}

	public Course updateCourseStatus(Optional<Course> course) {
		if (course.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado");
		}
		if (course.get().getStatus() == CourseStatus.INACTIVE) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Curso já inativado");
		}
		course.get().setStatus(CourseStatus.INACTIVE).setInactivateDate(LocalDateTime.now(ZoneId.of("UTC")));
		return course.get();
	}
}
