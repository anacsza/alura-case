package br.com.alura.helper.enrollment;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

import br.com.alura.model.Course;
import br.com.alura.model.Enrollment;
import br.com.alura.model.User;

@Component
public class EnrollmentHelper {

	public Enrollment createEnrollment(Course course, User user) {
		return new Enrollment().setCourse(course).setUser(user).setEnrollmentDate(LocalDateTime.now(ZoneId.of("UTC")));
	}
}
