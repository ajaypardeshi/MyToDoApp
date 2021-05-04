package com.avp.util;

public class BaseResult {
	
	private boolean success;
	
	private int status_code;
	
	private String message;
	
	private Object data;
	
	
	public BaseResult()
	{
		
	}
	
	public BaseResult(boolean success, int status_code, String message, Object data) {
		super();
		this.success = success;
		this.status_code = status_code;
		this.message = message;
		this.data = data;
	}



	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public int getStatus_code() {
		return status_code;
	}


	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "BaseResult [success=" + success + ", status_code=" + status_code + ", message=" + message + ", data="
				+ data + "]";
	}
	
	

}
