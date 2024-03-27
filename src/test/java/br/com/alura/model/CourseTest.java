package br.com.alura.model;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public final class CourseTest {

	public static Course build() throws NoSuchAlgorithmException {
		return new Course().setCode("spring-boot").setDescription("Spring Boot")
				.setInsertDate(LocalDateTime.now(ZoneId.of("UTC"))).setName("Spring Boot")
				.setStatus(CourseStatus.ACTIVE).setUser(UserTest.build());
	}
}
