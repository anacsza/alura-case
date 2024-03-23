package br.com.alura.repository.enrollment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.model.Enrollment;
import br.com.alura.model.User;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

	Optional<Enrollment> findByUser(User user);

}
