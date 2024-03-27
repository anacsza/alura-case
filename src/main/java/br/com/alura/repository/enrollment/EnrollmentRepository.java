package br.com.alura.repository.enrollment;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.model.Course;
import br.com.alura.model.Enrollment;
import br.com.alura.model.EnrollmentProjection;
import br.com.alura.model.User;

@Repository
public interface EnrollmentRepository extends PagingAndSortingRepository<Enrollment, Long> {

	Optional<Enrollment> findByUserAndCourse(User user, Course course);

	void save(Enrollment enrollment);

	@Query(value = "SELECT COUNT(e.course_id) AS enrollments, "
			+ "(SUM(CASE WHEN e.score >= 9 THEN 1 ELSE 0 END) / COUNT(e.course_id) * 100 - "
			+ "SUM(CASE WHEN e.score < 6 THEN 1 ELSE 0 END) / COUNT(e.course_id) * 100) AS nps, " + "c.code, "
			+ "c.name, " + "c.description, "
			+ "CASE WHEN c.status = 'ACTIVE' THEN 'Ativo' ELSE 'Inativo' END AS courseStatus " + "FROM enrollment e "
			+ "INNER JOIN course c ON c.id = e.course_id "
			+ "WHERE c.status = :status AND e.score_description IS NOT NULL " + "GROUP BY e.course_id "
			+ "HAVING enrollments >= :qtdeEnrollmentCourse "
			+ "ORDER BY enrollments DESC, nps DESC, c.name ASC", nativeQuery = true)
	Page<EnrollmentProjection> findByStatusGroupByNameDescriptionStatusOrderByEnrollmentCourses(
			@Param("qtdeEnrollmentCourse") int qtdeEnrollmentCourse, @Param("status") String status, Pageable pageable);

}
