package br.com.alura.helper.course;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.model.Course;
import br.com.alura.model.CourseStatus;
import br.com.alura.model.User;
import br.com.alura.model.UserRole;
import br.com.alura.resource.request.v1.CourseRequest;
import br.com.alura.resource.response.v1.CourseResponse;

@Component
public class CourseHelper {

	public List<CourseResponse> createCourseResponse(List<Course> courses) {
		return courses.stream().map(CourseHelper::createCourseResponse).collect(Collectors.toList());
	}

	public static CourseResponse createCourseResponse(Course course) {
		return new CourseResponse().setCode(course.getCode()).setDescription(course.getDescription())
				.setName(course.getName()).setInsertDate(course.getInsertDate())
				.setInactivateDate(course.getInactivateDate()).setStatus(course.getStatus()).setNps(0);
	}

	public Course createCourse(CourseRequest courseRequest, Optional<User> userInstructor) {
		if (userInstructor.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrato");
		}
		if (userInstructor.get().getRole() != UserRole.INSTRUCTOR) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não é instrutor");
		}
		return new Course().setCode(courseRequest.getCode()).setDescription(courseRequest.getDescription())
				.setName(courseRequest.getName()).setInsertDate(LocalDateTime.now(ZoneId.of("UTC")))
				.setStatus(CourseStatus.ACTIVE).setUser(userInstructor.get());
	}
}
