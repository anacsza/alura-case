package br.com.alura.repository.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.model.Course;
import br.com.alura.model.CourseStatus;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	List<Course> findByStatus(CourseStatus status);

	Optional<Course> findByCode(String code);

}
