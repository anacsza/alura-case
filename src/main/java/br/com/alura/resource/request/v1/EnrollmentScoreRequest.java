package br.com.alura.resource.request.v1;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EnrollmentScoreRequest extends EnrollmentRequest {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Max(10)
	private int score;

	@NotNull
	@NotBlank
	@Size(max = 255)
	private String scoreDescription;

	public EnrollmentScoreRequest() {

	}

	public int getScore() {
		return score;
	}

	public EnrollmentScoreRequest setScore(int score) {
		this.score = score;
		return this;
	}

	public String getScoreDescription() {
		return scoreDescription;
	}

	public EnrollmentScoreRequest setScoreDescription(String scoreDescription) {
		this.scoreDescription = scoreDescription;
		return this;
	}

	@Override
	public String toString() {
		return "EnrollmentScoreRequest [score=" + score + ", scoreDescription=" + scoreDescription + ", getUsername()="
				+ getUsername() + ", getCode()=" + getCode() + "]";
	}

}
