package br.com.alura.resource.v1;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.resource.request.v1.UserRequest;
import br.com.alura.resource.response.BaseResponse;
import br.com.alura.resource.response.v1.UserResponse;
import br.com.alura.service.user.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v1/users", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class UserResource {

	private static final Logger LOGGER = getLogger(UserResource.class);

	@Autowired
	private UserService userService;

	@PostMapping
	public BodyBuilder createUser(@Valid @RequestBody UserRequest userRequest) throws URISyntaxException {
		LOGGER.info("createUser name={}", userRequest.getName());
		userService.createUser(userRequest);
		return ResponseEntity.created(new URI("v1/users?username=" + userRequest.getUsername()));
	}

	@GetMapping
	public ResponseEntity<BaseResponse<UserResponse>> getUser(@Valid @RequestParam String username) {
		LOGGER.info("getUser name={}", username);
		UserResponse userResponse = userService.getUser(username);
		BaseResponse<UserResponse> baseResponse = new BaseResponse<>(userResponse);
		return ResponseEntity.ok(baseResponse);
	}

}
