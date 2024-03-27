package br.com.alura.resource.response;

import java.io.Serializable;
import java.util.List;

public class BaseErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private int statusCode;

	private String message;

	private List<String> details;

	public BaseErrorResponse() {

	}

	public int getStatusCode() {
		return statusCode;
	}

	public BaseErrorResponse setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public BaseErrorResponse setMessage(String message) {
		this.message = message;
		return this;
	}

	public List<String> getDetails() {
		return details;
	}

	public BaseErrorResponse setDetails(List<String> details) {
		this.details = details;
		return this;
	}

}
