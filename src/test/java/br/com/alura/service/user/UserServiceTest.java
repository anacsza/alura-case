package br.com.alura.service.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import br.com.alura.helper.user.UserHelper;
import br.com.alura.model.User;
import br.com.alura.model.UserTest;
import br.com.alura.repository.user.UserRepository;
import br.com.alura.resource.request.v1.UserRequest;
import br.com.alura.resource.request.v1.UserRequestTest;
import br.com.alura.resource.response.v1.UserResponse;
import br.com.alura.resource.response.v1.UserResponseTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	private UserService service;

	@Mock
	private UserRepository repository;

	@Mock
	private UserHelper helper;

	private UserRequest userRequest;

	private String username;

	private Optional<User> userFound;

	private UserResponse response;

	@BeforeEach
	public void setup() throws NoSuchAlgorithmException {
		userRequest = UserRequestTest.build();
		username = "ana";
		userFound = Optional.of(UserTest.build());
		response = UserResponseTest.build();
	}

	@Description("WhenCreateUser_GivenValidRequest_ThenSuccessfullyRegistered")
	@Test
	public void createUser() throws NoSuchAlgorithmException, URISyntaxException {
		// Arrange
		doNothing().when(helper).validateUserRequest(any());
		when(repository.findByUsernameOrEmail(any(), any())).thenReturn(Optional.empty());
		when(helper.createUser(any(), any())).thenReturn(userFound.get());
		// Act
		// Assert
		assertDoesNotThrow(() -> {
			service.createUser(userRequest);
		});
	}

	@Description("WhenGetUser_GivenValidParams_ThenReturnUserFound")
	@Test
	public void getUser() {
		// Arrange
		when(repository.findByUsername(any())).thenReturn(userFound);
		when(helper.createUserResponse(any())).thenReturn(response);
		// Act
		UserResponse response = service.getUser(username);
		// Assert
		assertNotNull(username);
		assertNotNull(response);
		assertEquals(userFound.get().getName(), response.getName());
		assertEquals(userFound.get().getEmail(), response.getEmail());
		assertEquals(userFound.get().getRole(), response.getRole());
	}

}
