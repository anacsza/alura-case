package br.com.alura.resource.v1;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.model.CourseStatus;
import br.com.alura.resource.request.v1.EnrollmentRequest;
import br.com.alura.resource.request.v1.EnrollmentScoreRequest;
import br.com.alura.resource.response.BaseResponse;
import br.com.alura.resource.response.v1.EnrollmentResponse;
import br.com.alura.service.enrollment.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Enrollments API")
@RestController
@RequestMapping(path = "/v1/enrollments", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class EnrollmentResource {

	private static final Logger LOGGER = getLogger(EnrollmentResource.class);

	@Autowired
	private EnrollmentService enrollmentService;

	@Operation(summary = "Course enrollment")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Enrollment successful"),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Course or user not found", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json") }) })
	@PostMapping
	public ResponseEntity<?> createEnrollment(@Valid @RequestBody EnrollmentRequest enrollmentRequest) {
		LOGGER.info("createEnrollment code={} username={}", enrollmentRequest.getCode(), enrollmentRequest.getUsername());
		enrollmentService.createEnrollment(enrollmentRequest);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "Register course feedback")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Feedback registered"),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Course or user not found", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json") }) })
	@PostMapping(path = "/feedbacks")
	public ResponseEntity<?> createEnrollmentScore(@Valid @RequestBody EnrollmentScoreRequest enrollmentScoreRequest) {
		LOGGER.info("createEnrollmentScore code={} username={}", enrollmentScoreRequest.getCode(), enrollmentScoreRequest.getUsername());
		enrollmentService.createEnrollmentScore(enrollmentScoreRequest);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "Course enrollments report")
	@ApiResponses(value = { @ApiResponse(responseCode = "206", description = "Courses enrollments found"),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping
	public ResponseEntity<BaseResponse<Page<EnrollmentResponse>>> getEnrollment(
			@RequestParam(defaultValue = "4") int qtdeEnrollmentCourse,
			@RequestParam(defaultValue = "ACTIVE") CourseStatus courseStatus,
			@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int page) {
		LOGGER.info("getEnrollment qtdeEnrollmentCourse={} page={} limit={}", qtdeEnrollmentCourse, page, limit);
		Page<EnrollmentResponse> enrollmentList = enrollmentService.getEnrollment(qtdeEnrollmentCourse, courseStatus,
				limit, page);
		return new ResponseEntity<>(new BaseResponse<>(enrollmentList), HttpStatus.PARTIAL_CONTENT);
	}
}
