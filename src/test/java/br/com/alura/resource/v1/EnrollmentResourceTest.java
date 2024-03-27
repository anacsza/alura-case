package br.com.alura.resource.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.alura.model.CourseStatus;
import br.com.alura.resource.request.v1.EnrollmentRequest;
import br.com.alura.resource.request.v1.EnrollmentRequestTest;
import br.com.alura.resource.request.v1.EnrollmentScoreRequest;
import br.com.alura.resource.request.v1.EnrollmentScoreRequestTest;
import br.com.alura.resource.response.BaseResponse;
import br.com.alura.resource.response.v1.EnrollmentResponse;
import br.com.alura.resource.response.v1.EnrollmentResponseTest;
import br.com.alura.service.enrollment.EnrollmentService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EnrollmentResourceTest {

	@InjectMocks
	private EnrollmentResource resource;

	@Mock
	private EnrollmentService service;

	private EnrollmentRequest enrollmentRequest;

	private EnrollmentScoreRequest enrollmentScoreRequest;

	private int qtdeEnrollmentCourse;

	private CourseStatus courseStatus;

	private int limit;

	private int page;

	private EnrollmentResponse enrollmentResponse;

	private Page<EnrollmentResponse> pageResponse;

	private BaseResponse<Page<EnrollmentResponse>> baseResponse;

	@BeforeEach
	public void setup() {
		enrollmentRequest = EnrollmentRequestTest.build();
		enrollmentScoreRequest = EnrollmentScoreRequestTest.build();
		enrollmentResponse = EnrollmentResponseTest.build();
		pageResponse = new PageImpl<EnrollmentResponse>(List.of(enrollmentResponse));
		baseResponse = new BaseResponse<>(pageResponse);
		qtdeEnrollmentCourse = 4;
		courseStatus = CourseStatus.ACTIVE;
		limit = 10;
		page = 0;
	}

	@Description("WhenCreateEnrollment_GivenValidRequest_ThenSuccessfullyRegistered")
	@Test
	public void createEnrollment() {
		// Arrange
		doNothing().when(service).createEnrollment(any());
		// Act
		ResponseEntity<?> response = resource.createEnrollment(enrollmentRequest);
		// Assert
		assertNotNull(enrollmentRequest);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Description("WhenCreateEnrollmentScore_GivenValidRequest_ThenSuccessfullyChanged")
	@Test
	public void createEnrollmentScore() {
		// Arrange
		doNothing().when(service).createEnrollmentScore(any());
		// Act
		ResponseEntity<?> response = resource.createEnrollmentScore(enrollmentScoreRequest);
		// Assert
		assertNotNull(enrollmentRequest);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Description("WhenGetEnrollment_GivenValidParams_ThenReturnCourseList")
	@Test
	public void getEnrollment() {
		// Arrange
		when(service.getEnrollment(anyInt(), any(), anyInt(), anyInt())).thenReturn(pageResponse);
		// Act
		ResponseEntity<BaseResponse<Page<EnrollmentResponse>>> response = resource.getEnrollment(qtdeEnrollmentCourse,
				courseStatus, page, limit);
		// Assert
		assertNotNull(pageResponse);
		assertNotNull(qtdeEnrollmentCourse);
		assertNotNull(courseStatus);
		assertNotNull(response);
		assertEquals(HttpStatus.PARTIAL_CONTENT, response.getStatusCode());
		assertEquals(baseResponse.getData().getContent().get(0).getName(),
				response.getBody().getData().getContent().get(0).getName());
		assertEquals(baseResponse.getData().getContent().get(0).getCode(),
				response.getBody().getData().getContent().get(0).getCode());
		assertEquals(baseResponse.getData().getContent().get(0).getDescription(),
				response.getBody().getData().getContent().get(0).getDescription());
		assertEquals(baseResponse.getData().getContent().get(0).getCourseStatus(),
				response.getBody().getData().getContent().get(0).getCourseStatus());
		assertEquals(baseResponse.getData().getContent().get(0).getEnrollments(),
				response.getBody().getData().getContent().get(0).getEnrollments());
		assertEquals(baseResponse.getData().getContent().get(0).getNps(),
				response.getBody().getData().getContent().get(0).getNps());
	}
}
