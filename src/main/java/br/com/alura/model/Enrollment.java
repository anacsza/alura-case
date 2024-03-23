package br.com.alura.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "enrollment")
public class Enrollment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "enrollment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private LocalDateTime enrollmentDate;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;

	private int score;

	@Column(name = "score_description")
	private String scoreDescription;

	public Enrollment() {

	}

	public LocalDateTime getEnrollmentDate() {
		return enrollmentDate;
	}

	public Enrollment setEnrollmentDate(LocalDateTime enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Enrollment setUser(User user) {
		this.user = user;
		return this;
	}

	public Course getCourse() {
		return course;
	}

	public Enrollment setCourse(Course course) {
		this.course = course;
		return this;
	}

	public int getScore() {
		return score;
	}

	public Enrollment setScore(int score) {
		this.score = score;
		return this;
	}

	public String getScoreDescription() {
		return scoreDescription;
	}

	public Enrollment setScoreDescription(String scoreDescription) {
		this.scoreDescription = scoreDescription;
		return this;
	}
}
