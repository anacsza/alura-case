package br.com.alura.resource.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
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
import br.com.alura.resource.request.v1.CourseRequest;
import br.com.alura.resource.request.v1.CourseRequestTest;
import br.com.alura.resource.response.BaseResponse;
import br.com.alura.resource.response.v1.CourseResponse;
import br.com.alura.resource.response.v1.CourseResponseTest;
import br.com.alura.service.course.CourseService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CourseResourceTest {

	@InjectMocks
	private CourseResource resource;

	@Mock
	private CourseService service;

	private CourseRequest courseRequest;

	private String code;

	private CourseResponse course;

	private Page<CourseResponse> pageResponse;

	private BaseResponse<Page<CourseResponse>> baseResponse;

	private CourseStatus status;

	private int limit;

	private int page;

	@BeforeEach
	public void setup() {
		courseRequest = CourseRequestTest.build();
		code = "spring-boot";
		course = CourseResponseTest.build();
		pageResponse = new PageImpl<CourseResponse>(List.of(course));
		baseResponse = new BaseResponse<>(pageResponse);
		status = CourseStatus.ACTIVE;
		limit = 10;
		page = 0;
	}

	@Description("WhenCreateCourse_GivenValidRequest_ThenSuccessfullyRegistered")
	@Test
	public void createCourse() throws NoSuchAlgorithmException, URISyntaxException {
		// Arrange
		doNothing().when(service).createCourse(any());
		// Act
		ResponseEntity<?> response = resource.createCourse(courseRequest);
		// Assert
		assertNotNull(courseRequest);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("v1/courses?code=" + courseRequest.getCode(), response.getHeaders().getLocation().toString());
	}

	@Description("WhenChangeCourseStatus_GivenValidRequest_ThenSuccessfullyChanged")
	@Test
	public void changeCourseStatus() {
		// Arrange
		doNothing().when(service).changeCourseStatus(any());
		// Act
		ResponseEntity<?> response = resource.changeCourseStatus(code);
		// Assert
		assertNotNull(code);
		assertNotNull(response);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Description("WhenGetCourses_GivenValidParams_ThenReturnCourseList")
	@Test
	public void getUser() {
		// Arrange
		when(service.getCourseList(any(), anyInt(), anyInt())).thenReturn(pageResponse);
		// Act
		ResponseEntity<BaseResponse<Page<CourseResponse>>> response = resource.getCourseList(status, page, limit);
		// Assert
		assertNotNull(pageResponse);
		assertNotNull(status);
		assertNotNull(response);
		assertEquals(HttpStatus.PARTIAL_CONTENT, response.getStatusCode());
		assertEquals(baseResponse.getData().getContent().get(0).getName(),
				response.getBody().getData().getContent().get(0).getName());
		assertEquals(baseResponse.getData().getContent().get(0).getCode(),
				response.getBody().getData().getContent().get(0).getCode());
		assertEquals(baseResponse.getData().getContent().get(0).getDescription(),
				response.getBody().getData().getContent().get(0).getDescription());
		assertEquals(baseResponse.getData().getContent().get(0).getInactivateDate(),
				response.getBody().getData().getContent().get(0).getInactivateDate());
		assertEquals(baseResponse.getData().getContent().get(0).getInsertDate(),
				response.getBody().getData().getContent().get(0).getInsertDate());
		assertEquals(baseResponse.getData().getContent().get(0).getStatus(),
				response.getBody().getData().getContent().get(0).getStatus());
	}
}
