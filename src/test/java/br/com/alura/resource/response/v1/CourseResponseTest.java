package br.com.alura.resource.response.v1;

import java.time.LocalDateTime;
import java.time.ZoneId;

import br.com.alura.model.CourseStatus;

public final class CourseResponseTest {

	public static CourseResponse build() {
		return new CourseResponse().setCode("spring-boot").setDescription("Spring Boot")
				.setInsertDate(LocalDateTime.now(ZoneId.of("UTC"))).setName("Spring Boot")
				.setStatus(CourseStatus.ACTIVE);
	}
}
