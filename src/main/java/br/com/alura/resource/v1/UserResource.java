package br.com.alura.resource.v1;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Users API")
@RestController
@RequestMapping(path = "/v1/users", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class UserResource {

	private static final Logger LOGGER = getLogger(UserResource.class);

	@Autowired
	private UserService userService;

	@Operation(summary = "Register user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Course successfully registered", headers = {
					@Header(name = "location", description = "The URI returns the resource", schema = @Schema(type = "string")) }, content = {
							@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json") }) })
	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest)
			throws URISyntaxException, NoSuchAlgorithmException {
		LOGGER.info("createUser username={}", userRequest.getUsername());
		userService.createUser(userRequest);
		return ResponseEntity.created(new URI("v1/users?username=" + userRequest.getUsername())).build();
	}

	@Operation(summary = "User information")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User found"),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "User not found", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping
	public ResponseEntity<BaseResponse<UserResponse>> getUser(@Valid @RequestParam String username) {
		LOGGER.info("getUser username={}", username);
		UserResponse userResponse = userService.getUser(username);
		return ResponseEntity.ok(new BaseResponse<>(userResponse));
	}

}
