package br.com.alura.resource.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.alura.resource.request.v1.UserRequest;
import br.com.alura.resource.request.v1.UserRequestTest;
import br.com.alura.resource.response.BaseResponse;
import br.com.alura.resource.response.v1.UserResponse;
import br.com.alura.resource.response.v1.UserResponseTest;
import br.com.alura.service.user.UserService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserResourceTest {

	@InjectMocks
	private UserResource userResource;

	@Mock
	private UserService service;

	private UserRequest userRequest;

	private String username;

	private UserResponse userResponse;

	private BaseResponse<UserResponse> baseResponse;

	@BeforeEach
	public void setup() {
		userRequest = UserRequestTest.build();
		username = "ana";
		userResponse = UserResponseTest.build();
		baseResponse = new BaseResponse<>(userResponse);
	}

	@Description("WhenCreateUser_GivenValidRequest_ThenSuccessfullyRegistered")
	@Test
	public void createUser() throws NoSuchAlgorithmException, URISyntaxException {
		// Arrange
		doNothing().when(service).createUser(any());
		// Act
		ResponseEntity<?> response = userResource.createUser(userRequest);
		// Assert
		assertNotNull(userRequest);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("v1/users?username=" + userRequest.getUsername(), response.getHeaders().getLocation().toString());
	}

	@Description("WhenGetUser_GivenValidParams_ThenReturnUserFound")
	@Test
	public void getUser() {
		// Arrange
		when(service.getUser(any())).thenReturn(userResponse);
		// Act
		ResponseEntity<BaseResponse<UserResponse>> response = userResource.getUser(username);
		// Assert
		assertNotNull(userResponse);
		assertNotNull(username);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(baseResponse.getData().getName(), response.getBody().getData().getName());
		assertEquals(baseResponse.getData().getEmail(), response.getBody().getData().getEmail());
		assertEquals(baseResponse.getData().getRole(), response.getBody().getData().getRole());
	}
}
