package br.com.alura.helper.user;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.model.User;
import br.com.alura.resource.request.v1.UserRequest;
import br.com.alura.resource.response.v1.UserResponse;

@Component
public class UserHelper {

	public UserResponse createUserResponse(Optional<User> user) {
		if (user.isPresent()) {
			return new UserResponse().setName(user.get().getName()).setEmail(user.get().getEmail())
					.setRole(user.get().getRole());
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrato");
	}

	public User createUser(UserRequest userRequest) {
		return new User().setName(userRequest.getName()).setUsername(userRequest.getUsername())
				.setEmail(userRequest.getEmail()).setPassword(userRequest.getPassword()).setRole(userRequest.getRole())
				.setInsertDate(LocalDateTime.now(ZoneId.of("UTC")));
	}
}
