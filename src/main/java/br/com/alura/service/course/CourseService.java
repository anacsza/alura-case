package br.com.alura.service.course;

import java.util.Optional;

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

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseHelper courseHelper;

	public void createCourse(CourseRequest courseRequest) {
		courseHelper.validateUserRequest(courseRequest);
		Optional<User> userFound = userRepository.findByUsername(courseRequest.getInstructorUsername());
		Optional<Course> courseFound = courseRepository.findByCode(courseRequest.getCode());
		courseRepository.save(courseHelper.createCourse(courseRequest, userFound, courseFound));
	}

	public void changeCourseStatus(String code) {
		Optional<Course> course = courseRepository.findByCode(code);
		courseRepository.save(courseHelper.updateCourseStatus(course));
	}

	public Page<CourseResponse> getCourseList(CourseStatus status, int page, int limit) {
		Page<Course> courseList = courseRepository.findByStatus(status, PageRequest.of(page, limit));
		return courseHelper.createCourseResponse(courseList);
	}

}
