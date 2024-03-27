package br.com.alura.service.course;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.net.URISyntaxException;
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

import br.com.alura.helper.course.CourseHelper;
import br.com.alura.model.Course;
import br.com.alura.model.CourseStatus;
import br.com.alura.model.CourseTest;
import br.com.alura.model.User;
import br.com.alura.model.UserTest;
import br.com.alura.repository.course.CourseRepository;
import br.com.alura.repository.user.UserRepository;
import br.com.alura.resource.request.v1.CourseRequest;
import br.com.alura.resource.request.v1.CourseRequestTest;
import br.com.alura.resource.response.v1.CourseResponse;
import br.com.alura.resource.response.v1.CourseResponseTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

	@InjectMocks
	private CourseService service;

	@Mock
	private CourseRepository repository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private CourseHelper helper;

	private CourseRequest request;

	private String code;

	private Optional<Course> courseFound;

	private Optional<User> userFound;

	private CourseResponse response;

	private CourseStatus status;

	private int limit;

	private int page;

	private Page<Course> pageResponse;

	private Page<CourseResponse> pageCourseResponse;

	@BeforeEach
	public void setup() throws NoSuchAlgorithmException {
		request = CourseRequestTest.build();
		code = "spring-boot";
		courseFound = Optional.of(CourseTest.build());
		userFound = Optional.of(UserTest.build());
		response = CourseResponseTest.build();
		status = CourseStatus.ACTIVE;
		limit = 10;
		page = 0;
		pageResponse = new PageImpl<Course>(List.of(courseFound.get()));
		pageCourseResponse = new PageImpl<CourseResponse>(List.of(response));
	}

	@Description("WhenCreateCourse_GivenValidRequest_ThenSuccessfullyRegistered")
	@Test
	public void createCourse() throws NoSuchAlgorithmException, URISyntaxException {
		// Arrange
		doNothing().when(helper).validateUserRequest(any());
		when(userRepository.findByUsername(any())).thenReturn(userFound);
		when(repository.findByCode(any())).thenReturn(Optional.empty());
		when(helper.createCourse(any(), any(), any())).thenReturn(courseFound.get());
		doNothing().when(repository).save(any());
		// Act
		// Assert
		assertDoesNotThrow(() -> {
			service.createCourse(request);
		});
	}

	@Description("WhenChangeCourseStatus_GivenValidRequest_ThenSuccessfullyChanged")
	@Test
	public void changeCourseStatus() {
		// Arrange
		when(repository.findByCode(any())).thenReturn(courseFound);
		when(helper.updateCourseStatus(any())).thenReturn(courseFound.get());
		doNothing().when(repository).save(any());
		// Act
		// Assert
		assertDoesNotThrow(() -> {
			service.changeCourseStatus(code);
		});
	}

	@Description("WhenGetCourses_GivenValidParams_ThenReturnCourseList")
	@Test
	public void getUser() {
		// Arrange
		when(repository.findByStatus(any(), any())).thenReturn(pageResponse);
		when(helper.createCourseResponse(ArgumentMatchers.<Page<Course>>any())).thenReturn(pageCourseResponse);
		// Act
		Page<CourseResponse> response = service.getCourseList(status, page, limit);
		// Assert
		assertNotNull(status);
		assertNotNull(response);
		assertEquals(pageResponse.getContent().get(0).getName(), response.getContent().get(0).getName());
		assertEquals(pageResponse.getContent().get(0).getCode(), response.getContent().get(0).getCode());
		assertEquals(pageResponse.getContent().get(0).getDescription(), response.getContent().get(0).getDescription());
		assertEquals(pageResponse.getContent().get(0).getInactivateDate(),
				response.getContent().get(0).getInactivateDate());
		assertEquals(pageResponse.getContent().get(0).getInsertDate().getDayOfYear(),
				response.getContent().get(0).getInsertDate().getDayOfYear());
		assertEquals(pageResponse.getContent().get(0).getInsertDate().getDayOfMonth(),
				response.getContent().get(0).getInsertDate().getDayOfMonth());
		assertEquals(pageResponse.getContent().get(0).getInsertDate().getDayOfWeek(),
				response.getContent().get(0).getInsertDate().getDayOfWeek());
		assertEquals(pageResponse.getContent().get(0).getInsertDate().getHour(),
				response.getContent().get(0).getInsertDate().getHour());
		assertEquals(pageResponse.getContent().get(0).getInsertDate().getMinute(),
				response.getContent().get(0).getInsertDate().getMinute());
		assertEquals(pageResponse.getContent().get(0).getInsertDate().getSecond(),
				response.getContent().get(0).getInsertDate().getSecond());
		assertEquals(pageResponse.getContent().get(0).getStatus(), response.getContent().get(0).getStatus());
	}

}
