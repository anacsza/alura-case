package br.com.alura.resource.v1;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.resource.request.v1.EnrollmentRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v1/enrollments", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class EnrollmentResource {
	private static final Logger LOGGER = getLogger(EnrollmentResource.class);

	@PostMapping
	public BodyBuilder createEnrollment(@Valid @RequestBody EnrollmentRequest enrollmentRequest) {
		LOGGER.info("createEnrollment username={}", enrollmentRequest.getUsername());
		// TODO
		return ResponseEntity.ok();
	}
}
