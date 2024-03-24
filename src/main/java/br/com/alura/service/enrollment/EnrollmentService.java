package br.com.alura.service.enrollment;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.helper.enrollment.EnrollmentHelper;
import br.com.alura.model.Course;
import br.com.alura.model.CourseStatus;
import br.com.alura.model.Enrollment;
import br.com.alura.model.EnrollmentProjection;
import br.com.alura.model.User;
import br.com.alura.repository.course.CourseRepository;
import br.com.alura.repository.enrollment.EnrollmentRepository;
import br.com.alura.repository.user.UserRepository;
import br.com.alura.resource.request.v1.EnrollmentRequest;
import br.com.alura.resource.request.v1.EnrollmentScoreRequest;
import br.com.alura.resource.response.v1.EnrollmentResponse;
import br.com.alura.sender.EmailSender;

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

	@Autowired
	private EmailSender emailSender;

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
		emailSender.send(enrollmentFounded.get().getCourse().getUser().getEmail(),
				"Feedback Curso " + enrollmentFounded.get().getCourse().getName(),
				"Você recebeu a nota: " + enrollmentFounded.get().getScore() + " devido ao motivo: "
						+ enrollmentFounded.get().getScoreDescription());
	}

	public Page<EnrollmentResponse> getEnrollment(int qtdeEnrollmentCourse, CourseStatus courseStatus, int limit,
			int page) {
		LOGGER.info("getEnrollment qtdeEnrollmentCourse={} page={} limit={}", qtdeEnrollmentCourse, page, limit);
		Page<EnrollmentProjection> enrollmentList = enrollmentRepository
				.findByStatusGroupByNameDescriptionStatusOrderByEnrollmentCourses(qtdeEnrollmentCourse,
						courseStatus.name(), PageRequest.of(page, limit));
		LOGGER.info("enrollmentList qtdeEnrollmentCourse={} page={} limit={}", enrollmentList.getTotalElements(), page,
				limit);
		return enrollmentHelper.createEnrollmentResponse(enrollmentList);
	}

}
