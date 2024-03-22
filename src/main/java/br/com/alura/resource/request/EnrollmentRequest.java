package br.com.alura.resource.request;

import java.io.Serializable;

public class EnrollmentRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

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

}
