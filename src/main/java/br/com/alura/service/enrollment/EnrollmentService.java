package br.com.alura.service.enrollment;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import br.com.alura.resource.request.v1.EnrollmentRequest;

@Service
public class EnrollmentService {
	private static final Logger LOGGER = getLogger(EnrollmentService.class);

	public void createEnrollment(EnrollmentRequest enrollmentRequest) {
		LOGGER.info("createEnrollment username={}", enrollmentRequest.getUsername());
		// TODO
	}
}
