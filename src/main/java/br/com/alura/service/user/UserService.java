package br.com.alura.service.user;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.helper.user.UserHelper;
import br.com.alura.model.User;
import br.com.alura.repository.user.UserRepository;
import br.com.alura.resource.request.v1.UserRequest;
import br.com.alura.resource.response.v1.UserResponse;

@Service
public class UserService {

	private static final Logger LOGGER = getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserHelper userHelper;

	public void createUser(UserRequest userRequest) {
		LOGGER.info("createUser name={}", userRequest.getName());
		userRepository.save(userHelper.createUser(userRequest));
	}

	public UserResponse getUser(String username) {
		LOGGER.info("getUser name={}", username);
		Optional<User> user = userRepository.findByUsername(username);
		return userHelper.createUserResponse(user);
	}

}
