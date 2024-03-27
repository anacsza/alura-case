package br.com.alura.service.enrollment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.alura.helper.enrollment.EnrollmentHelper;
import br.com.alura.model.Course;
import br.com.alura.model.CourseStatus;
import br.com.alura.model.CourseTest;
import br.com.alura.model.Enrollment;
import br.com.alura.model.EnrollmentProjection;
import br.com.alura.model.EnrollmentTest;
import br.com.alura.model.User;
import br.com.alura.model.UserTest;
import br.com.alura.repository.course.CourseRepository;
import br.com.alura.repository.enrollment.EnrollmentRepository;
import br.com.alura.repository.user.UserRepository;
import br.com.alura.resource.request.v1.EnrollmentRequest;
import br.com.alura.resource.request.v1.EnrollmentRequestTest;
import br.com.alura.resource.request.v1.EnrollmentScoreRequest;
import br.com.alura.resource.request.v1.EnrollmentScoreRequestTest;
import br.com.alura.resource.response.BaseResponse;
import br.com.alura.resource.response.v1.EnrollmentResponse;
import br.com.alura.resource.response.v1.EnrollmentResponseTest;
import br.com.alura.sender.EmailSender;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

	@InjectMocks
	private EnrollmentService service;

	@Mock
	private EnrollmentRepository enrollmentRepository;

	@Mock
	private CourseRepository courseRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private EnrollmentHelper enrollmentHelper;

	@Mock
	private EmailSender emailSender;

	@Mock
	private EnrollmentProjection enrollmentProjection;

	private Optional<User> user;

	private Optional<Course> course;

	private Optional<Enrollment> enrollmentFound;

	private EnrollmentRequest enrollmentRequest;

	private EnrollmentScoreRequest enrollmentScoreRequest;

	private int qtdeEnrollmentCourse;

	private CourseStatus courseStatus;

	private int limit;

	private int page;

	private EnrollmentResponse enrollmentResponse;

	private Page<EnrollmentResponse> pageResponse;

	private Page<EnrollmentProjection> pageProjection;

	private BaseResponse<Page<EnrollmentResponse>> baseResponse;

	@BeforeEach
	public void setup() throws NoSuchAlgorithmException {
		user = Optional.of(UserTest.build());
		course = Optional.of(CourseTest.build());
		enrollmentFound = Optional.of(EnrollmentTest.build());

		enrollmentRequest = EnrollmentRequestTest.build();
		enrollmentScoreRequest = EnrollmentScoreRequestTest.build();
		enrollmentResponse = EnrollmentResponseTest.build();

		pageResponse = new PageImpl<EnrollmentResponse>(List.of(enrollmentResponse));
		pageProjection = new PageImpl<EnrollmentProjection>(List.of(enrollmentProjection));
		baseResponse = new BaseResponse<>(pageResponse);
		qtdeEnrollmentCourse = 1;
		courseStatus = CourseStatus.ACTIVE;
		limit = 10;
		page = 0;
	}

	@Description("WhenCreateEnrollment_GivenValidRequest_ThenSuccessfullyRegistered")
	@Test
	public void createEnrollment() {
		// Arrange
		when(userRepository.findByUsername(anyString())).thenReturn(user);
		when(courseRepository.findByCode(anyString())).thenReturn(course);
		when(enrollmentRepository.findByUserAndCourse(any(), any())).thenReturn(Optional.empty());
		when(enrollmentHelper.createEnrollment(any(), any())).thenReturn(enrollmentFound.get());
		doNothing().when(enrollmentRepository).save(any());
		// Act
		// Assert
		assertDoesNotThrow(() -> {
			service.createEnrollment(enrollmentRequest);
		});
	}

	@Description("WhenCreateEnrollmentScore_GivenValidRequest_ThenSuccessfullyChanged")
	@Test
	public void createEnrollmentScore() {
		// Arrange
		when(userRepository.findByUsername(anyString())).thenReturn(user);
		when(courseRepository.findByCode(anyString())).thenReturn(course);
		when(enrollmentRepository.findByUserAndCourse(any(), any())).thenReturn(enrollmentFound);
		doNothing().when(enrollmentRepository).save(any());
		doNothing().when(emailSender).send(any(), any(), any());
		// Act
		// Assert
		assertDoesNotThrow(() -> {
			service.createEnrollmentScore(enrollmentScoreRequest);
		});
	}

	@Description("WhenGetEnrollment_GivenValidParams_ThenReturnCourseList")
	@Test
	public void getEnrollment() {
		// Arrange
		when(enrollmentRepository.findByStatusGroupByNameDescriptionStatusOrderByEnrollmentCourses(anyInt(), any(),
				any())).thenReturn(pageProjection);
		when(enrollmentHelper.createEnrollmentResponse(ArgumentMatchers.<Page<EnrollmentProjection>>any()))
				.thenReturn(pageResponse);
		// Act
		Page<EnrollmentResponse> response = service.getEnrollment(qtdeEnrollmentCourse, courseStatus, limit, page);
		// Assert
		assertNotNull(pageResponse);
		assertNotNull(qtdeEnrollmentCourse);
		assertNotNull(courseStatus);
		assertNotNull(response);
		assertEquals(baseResponse.getData().getContent().get(0).getName(), response.getContent().get(0).getName());
		assertEquals(baseResponse.getData().getContent().get(0).getCode(), response.getContent().get(0).getCode());
		assertEquals(baseResponse.getData().getContent().get(0).getDescription(),
				response.getContent().get(0).getDescription());
		assertEquals(baseResponse.getData().getContent().get(0).getCourseStatus(),
				response.getContent().get(0).getCourseStatus());
		assertEquals(baseResponse.getData().getContent().get(0).getEnrollments(),
				response.getContent().get(0).getEnrollments());
		assertEquals(baseResponse.getData().getContent().get(0).getNps(), response.getContent().get(0).getNps());
	}

}
