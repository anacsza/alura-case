package br.com.alura.model;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public final class EnrollmentTest {

	public static Enrollment build() throws NoSuchAlgorithmException {
		return new Enrollment().setCourse(CourseTest.build()).setEnrollmentDate(LocalDateTime.now(ZoneId.of("UTC")))
				.setEnrollments(1).setNps(100).setScore(10).setScoreDescription("Teste").setUser(UserTest.build());
	}
}
