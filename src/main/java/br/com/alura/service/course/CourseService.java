package br.com.alura.service.course;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
		courseHelper.validateUserRequest(courseRequest);
		Optional<User> userFounded = userRepository.findByUsername(courseRequest.getInstructorUsername());
		Optional<Course> courseFounded = courseRepository.findByCode(courseRequest.getCode());
		courseRepository.save(courseHelper.createCourse(courseRequest, userFounded, courseFounded));
	}

	public void changeCourseStatus(String code) {
		LOGGER.info("changeCourseStatus code={}", code);
		Optional<Course> course = courseRepository.findByCode(code);
		courseRepository.save(courseHelper.updateCourseStatus(course));
	}

	public Page<CourseResponse> getCourseList(CourseStatus status, int page, int limit) {
		LOGGER.info("getCourseList status={}", status);
		Page<Course> courseList = courseRepository.findByStatus(status, PageRequest.of(page, limit));
		return courseHelper.createCourseResponse(courseList);
	}

}
