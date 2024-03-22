package br.com.alura.service.user;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import br.com.alura.resource.request.v1.UserRequest;
import br.com.alura.resource.response.v1.UserResponse;

@Service
public class UserService {

	private static final Logger LOGGER = getLogger(UserService.class);

	public String createUser(UserRequest userRequest) {
		LOGGER.info("createUser name={}", userRequest.getName());
		// TODO
		return null;
	}

	public UserResponse getUser(String username) {
		LOGGER.info("getUser name={}", username);
		UserResponse userResponse = new UserResponse();
		// TODO
		return userResponse;
	}

}
