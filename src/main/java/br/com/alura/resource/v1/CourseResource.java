package br.com.alura.resource.v1;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.model.CourseStatus;
import br.com.alura.resource.request.v1.CourseRequest;
import br.com.alura.resource.response.BaseResponse;
import br.com.alura.resource.response.v1.CourseResponse;
import br.com.alura.service.course.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Courses API")
@RestController
@RequestMapping(path = "/v1/courses", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class CourseResource {

	private static final Logger LOGGER = getLogger(CourseResource.class);

	@Autowired
	private CourseService courseService;

	@Operation(summary = "Course register")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Course successfully registered", headers = {
					@Header(name = "location", description = "The URI returns the resource", schema = @Schema(type = "string")) }, content = {
							@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "User not found", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json") }) })
	@PostMapping
	public ResponseEntity<?> createCourse(@Valid @RequestBody CourseRequest courseRequest) throws URISyntaxException {
		LOGGER.info("createCourse code={}", courseRequest.getCode());
		courseService.createCourse(courseRequest);
		return ResponseEntity.created(new URI("v1/courses?code=" + courseRequest.getCode())).build();
	}

	@Operation(summary = "Course inactivation")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Course successfully inactivated"),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Course not found", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json") }) })
	@PatchMapping
	public ResponseEntity<?> changeCourseStatus(@Valid @RequestParam String code) {
		LOGGER.info("changeCourseStatus code={}", code);
		courseService.changeCourseStatus(code);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Courses report by status")
	@ApiResponses(value = { @ApiResponse(responseCode = "206", description = "Courses found"),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping
	public ResponseEntity<BaseResponse<Page<CourseResponse>>> getCourseList(@Valid @RequestParam CourseStatus status,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit) {
		LOGGER.info("getCourseList status={}", status);
		Page<CourseResponse> courseList = courseService.getCourseList(status, page, limit);
		return new ResponseEntity<>(new BaseResponse<>(courseList), HttpStatus.PARTIAL_CONTENT);
	}
}
