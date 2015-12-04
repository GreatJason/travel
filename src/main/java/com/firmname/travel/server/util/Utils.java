package com.firmname.travel.server.util;

import com.google.common.base.CaseFormat;

public final class Utils {
	private Utils(){
		throw new Error("I am not supposed to be instantiated!");
	}
	
	public static String columnName2FieldName(String columnName) {
		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
	}

	public static boolean isValidEmail(String email){
		//TODO: to implement
		return true;
	}
	
	public static boolean isValidPhone(String phone){
		//TODO: to implement
		return true;
	}
	
}
