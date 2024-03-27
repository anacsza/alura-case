package br.com.alura.repository.course;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.model.Course;
import br.com.alura.model.CourseStatus;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

	void save(Course course);

	@EntityGraph(attributePaths = { "user" })
	Page<Course> findByStatus(CourseStatus status, Pageable pageable);

	Optional<Course> findByCode(String code);

}
