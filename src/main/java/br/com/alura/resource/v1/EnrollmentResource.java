package br.com.alura.resource.v1;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.resource.request.v1.EnrollmentRequest;
import br.com.alura.resource.request.v1.EnrollmentScoreRequest;
import br.com.alura.service.enrollment.EnrollmentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v1/enrollments", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class EnrollmentResource {

	private static final Logger LOGGER = getLogger(EnrollmentResource.class);

	@Autowired
	private EnrollmentService enrollmentService;

	@PostMapping
	public ResponseEntity<?> createEnrollment(@Valid @RequestBody EnrollmentRequest enrollmentRequest) {
		LOGGER.info("createEnrollment enrollmentRequest={}", enrollmentRequest.toString());
		enrollmentService.createEnrollment(enrollmentRequest);
		return ResponseEntity.ok().build();
	}

	@PostMapping(path = "/feedbacks")
	public ResponseEntity<?> createEnrollmentScore(@Valid @RequestBody EnrollmentScoreRequest enrollmentScoreRequest) {
		LOGGER.info("createEnrollment enrollmentScoreRequest={}", enrollmentScoreRequest.toString());
		enrollmentService.createEnrollmentScore(enrollmentScoreRequest);
		return ResponseEntity.ok().build();
	}
}
