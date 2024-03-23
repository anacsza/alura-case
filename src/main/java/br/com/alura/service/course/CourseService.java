package br.com.alura.service.course;

import static org.slf4j.LoggerFactory.getLogger;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.helper.course.CourseHelper;
import br.com.alura.model.Course;
import br.com.alura.model.CourseStatus;
import br.com.alura.model.User;
import br.com.alura.repository.course.CourseRepository;
import br.com.alura.repository.user.UserRepository;
import br.com.alura.resource.request.v1.CourseRequest;
import br.com.alura.resource.response.v1.CourseResponse;

@Service
public class CourseService {

	private static final Logger LOGGER = getLogger(CourseService.class);

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseHelper courseHelper;

	public void createCourse(CourseRequest courseRequest) {
		LOGGER.info("createCourse name={}", courseRequest.getName());
		Optional<User> user = userRepository.findByUsername(courseRequest.getInstructorUsername());
		courseRepository.save(courseHelper.createCourse(courseRequest, user));
	}

	public void changeCourseStatus(String code) {
		LOGGER.info("changeCourseStatus code={}", code);
		Optional<Course> course = courseRepository.findByCode(code);
		if (course.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado");
		}
		if (course.get().getStatus() == CourseStatus.INACTIVE) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Curso já inativado");
		}
		course.get().setStatus(CourseStatus.INACTIVE);
		course.get().setInactivateDate(LocalDateTime.now(ZoneId.of("UTC")));
		courseRepository.save(course.get());
	}

	public List<CourseResponse> getCourseList(CourseStatus status) {
		LOGGER.info("getCourseList status={}", status);
		List<Course> courseList = courseRepository.findByStatus(status);
		return courseHelper.createCourseResponse(courseList);
	}

}
