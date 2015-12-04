package com.firmname.travel.server.model;

public enum ErrorCode {
	Success(0, "Succeed"),
	/**
	 * 1-999 common error
	 */
	UNKNOWN(1, "Unknow error"),
	EXCEPTION(2, "Exception happened"),
	
	/**
	 * 1000-1999 user related error
	 */
	USER_ERROR(1000, "General user Error"),
	USER_INVALID(1001, "Invalid username or password"),
	USER_EXISTING(1002, ""),
	
	
	/**
	 * 2000-2999 DB related error
	 */
	DB_ERROR(2000, "DB error");
	
	
	private final int value;
	private final String errorMsg;
	
	private ErrorCode(final int value, String errorMessage){
		this.value = value;
		this.errorMsg = errorMessage;
	}
	
	public int intCode(){
		return value;
	}
	
	public String errorMessage(){
		return errorMsg;
	}
}
