package br.com.alura.resource.v1;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v1/courses", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class CourseResource {

	private static final Logger LOGGER = getLogger(CourseResource.class);

	@Autowired
	private CourseService courseService;

	@PostMapping
	public ResponseEntity<?> createCourse(@Valid @RequestBody CourseRequest courseRequest) throws URISyntaxException {
		LOGGER.info("createCourse name={}", courseRequest.toString());
		courseService.createCourse(courseRequest);
		return ResponseEntity.created(new URI("v1/courses?code=" + courseRequest.getCode())).build();
	}

	@PatchMapping
	public ResponseEntity<?> changeCourseStatus(@Valid @RequestParam String code) {
		LOGGER.info("changeCourseStatus code={}", code);
		courseService.changeCourseStatus(code);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<BaseResponse<List<CourseResponse>>> getCourseList(@Valid @RequestParam CourseStatus status) {
		LOGGER.info("getCourseList status={}", status);
		List<CourseResponse> courseList = courseService.getCourseList(status);
		return ResponseEntity.ok(new BaseResponse<>(courseList));
	}
}
