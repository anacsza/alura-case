package br.com.alura.helper.user;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.model.User;
import br.com.alura.resource.request.v1.UserRequest;
import br.com.alura.resource.response.BaseErrorResponse;
import br.com.alura.resource.response.v1.UserResponse;
import br.com.alura.util.DigestUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Component
public class UserHelper {

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();

	public UserResponse createUserResponse(Optional<User> user) {
		if (user.isPresent()) {
			return new UserResponse().setName(user.get().getName()).setEmail(user.get().getEmail())
					.setRole(user.get().getRole());
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrato");
	}

	public void validateUserRequest(UserRequest userRequest) {
		Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
		System.out.println(violations.toString());
		if (!violations.isEmpty()) {
			BaseErrorResponse baseErrorResponse = new BaseErrorResponse().setStatusCode(HttpStatus.BAD_REQUEST.value())
					.setMessage("Dados da requisição está inválido.").setDetails(new ArrayList<>());
			for (ConstraintViolation<UserRequest> violation : violations) {
				baseErrorResponse.getDetails().add(violation.getMessage());
			}
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "");
		}
	}

	public User createUser(UserRequest userRequest, Optional<User> userFound) throws NoSuchAlgorithmException {
		if (userFound.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado");
		}
		String password = DigestUtil.convertPassword(userRequest.getPassword());
		return new User().setName(userRequest.getName()).setUsername(userRequest.getUsername())
				.setEmail(userRequest.getEmail()).setPassword(password).setRole(userRequest.getRole())
				.setInsertDate(LocalDateTime.now(ZoneId.of("UTC")));
	}
}
