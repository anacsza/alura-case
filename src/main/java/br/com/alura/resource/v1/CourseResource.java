package br.com.alura.resource.v1;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.resource.request.v1.CourseRequest;
import br.com.alura.resource.response.BaseResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v1/courses", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class CourseResource {

	private static final Logger LOGGER = getLogger(CourseResource.class);

	@PostMapping
	public BodyBuilder createCourse(@Valid @RequestBody CourseRequest courseRequest) throws URISyntaxException {
		LOGGER.info("createCourse name={}", courseRequest.getName());
		// TODO
		return ResponseEntity.created(new URI(""));
	}

	@PatchMapping
	public HeadersBuilder<?> changeCourseStatus(@Valid @RequestParam String code) {
		LOGGER.info("changeCourseStatus code={}", code);
		// TODO
		return ResponseEntity.noContent();
	}

	@GetMapping
	public ResponseEntity<List<BaseResponse<CourseResource>>> getCourseList(@Valid @RequestParam String username) {
		LOGGER.info("getCourseList username={}", username);
		List<BaseResponse<CourseResource>> courseList = null;
		// TODO
		return ResponseEntity.ok(courseList);
	}
}
