package br.com.alura.resource.response;

public class BaseResponse<T> {

	private T data;

	public BaseResponse() {
		super();
	}

	public BaseResponse(T data) {
		super();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
