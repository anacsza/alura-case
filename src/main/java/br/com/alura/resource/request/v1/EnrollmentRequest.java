package br.com.alura.resource.request.v1;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;

public class EnrollmentRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@NotBlank
	@Pattern(regexp = "[a-z]{1,20}$", flags = Flag.CASE_INSENSITIVE)
	@Schema(example = "ana")
	private String username;

	@NotNull
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+(?:-[a-zA-Z]+){0,9}$", flags = Flag.CASE_INSENSITIVE)
	@Schema(example = "spring-boot")
	private String code;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "EnrollmentRequest [username=" + username + ", code=" + code + "]";
	}

}
