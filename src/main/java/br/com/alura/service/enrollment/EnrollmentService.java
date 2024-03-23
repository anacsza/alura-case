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
import br.com.alura.model.CourseStatus;
import br.com.alura.model.Enrollment;
import br.com.alura.model.User;
import br.com.alura.repository.course.CourseRepository;
import br.com.alura.repository.enrollment.EnrollmentRepository;
import br.com.alura.repository.user.UserRepository;
import br.com.alura.resource.request.v1.EnrollmentRequest;
import br.com.alura.resource.request.v1.EnrollmentScoreRequest;

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
		if (course.get().getStatus().equals(CourseStatus.INACTIVE)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matricula não permitida, curso inativo");
		}
		Optional<Enrollment> enrollmentFounded = enrollmentRepository.findByUserAndCourse(user.get(), course.get());
		if (enrollmentFounded.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Usuario não pode matricular-se em mais de um curso");
		}
		enrollmentRepository.save(enrollmentHelper.createEnrollment(course.get(), user.get()));
	}

	public void createEnrollmentScore(EnrollmentScoreRequest enrollmentScoreRequest) {
		LOGGER.info("createEnrollmentScore enrollmentScoreRequest={}", enrollmentScoreRequest.toString());
		enrollmentHelper.validateEnrollmentRequest(enrollmentScoreRequest);
		Optional<User> user = userRepository.findByUsername(enrollmentScoreRequest.getUsername());
		if (user.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrato");
		}
		Optional<Course> course = courseRepository.findByCode(enrollmentScoreRequest.getCode());
		if (course.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrato");
		}
		Optional<Enrollment> enrollmentFounded = enrollmentRepository.findByUserAndCourse(user.get(), course.get());
		if (enrollmentFounded.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Matrícula não encontrada");
		}
		enrollmentFounded.get().setScore(enrollmentScoreRequest.getScore());
		enrollmentFounded.get().setScoreDescription(enrollmentScoreRequest.getScoreDescription());
		enrollmentRepository.save(enrollmentFounded.get());
		sendFeedbackEmail(enrollmentFounded.get());
	}

	private void sendFeedbackEmail(Enrollment enrollment) {
		LOGGER.info("Simulating sending email to [%s]:\n".formatted(enrollment.getCourse().getUser().getEmail()));
		LOGGER.info("Subject: %s Body: %s".formatted(enrollment.getCourse().getUser().getEmail(),
				enrollment.getScoreDescription()));
	}
}
