package br.com.alura.service.user;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.helper.user.UserHelper;
import br.com.alura.model.User;
import br.com.alura.repository.user.UserRepository;
import br.com.alura.resource.request.v1.UserRequest;
import br.com.alura.resource.response.v1.UserResponse;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserHelper userHelper;

	public void createUser(UserRequest userRequest) throws NoSuchAlgorithmException {
		userHelper.validateUserRequest(userRequest);
		Optional<User> userFound = userRepository.findByUsernameOrEmail(userRequest.getUsername(),
				userRequest.getEmail());
		userRepository.save(userHelper.createUser(userRequest, userFound));
	}

	public UserResponse getUser(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return userHelper.createUserResponse(user);
	}

}
