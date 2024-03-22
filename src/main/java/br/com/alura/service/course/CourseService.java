package br.com.alura.service.course;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import br.com.alura.resource.request.v1.CourseRequest;
import br.com.alura.resource.response.BaseResponse;
import br.com.alura.resource.v1.CourseResource;

@Service
public class CourseService {

	private static final Logger LOGGER = getLogger(CourseService.class);

	public String createCourse(CourseRequest courseRequest) {
		LOGGER.info("createCourse name={}", courseRequest.getName());
		// TODO
		return null;
	}

	public void changeCourseStatus(String code) {
		LOGGER.info("changeCourseStatus code={}", code);
		// TODO
	}

	public List<BaseResponse<CourseResource>> getCourseList(String username) {
		LOGGER.info("getCourseList username={}", username);
		List<BaseResponse<CourseResource>> courseList = null;
		// TODO
		return courseList;
	}

}
