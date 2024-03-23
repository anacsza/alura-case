package br.com.alura.service.enrollment;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.helper.enrollment.EnrollmentHelper;
import br.com.alura.model.Course;
import br.com.alura.model.User;
import br.com.alura.repository.course.CourseRepository;
import br.com.alura.repository.enrollment.EnrollmentRepository;
import br.com.alura.repository.user.UserRepository;
import br.com.alura.resource.request.v1.EnrollmentRequest;

@Service
public class EnrollmentService {
	private static final Logger LOGGER = getLogger(EnrollmentService.class);

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EnrollmentHelper enrollmentHelper;

	public void createEnrollment(EnrollmentRequest enrollmentRequest) {
		LOGGER.info("createEnrollment username={}", enrollmentRequest.getUsername());
		Optional<User> user = userRepository.findByUsername(enrollmentRequest.getUsername());
		if (user.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrato");
		}
		Optional<Course> course = courseRepository.findByCode(enrollmentRequest.getCode());
		if (course.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrato");
		}
		enrollmentRepository.save(enrollmentHelper.createEnrollment(course.get(), user.get()));
	}
}
