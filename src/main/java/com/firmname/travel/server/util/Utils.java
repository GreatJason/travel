package com.firmname.travel.server.util;

import com.google.common.base.CaseFormat;

public class Utils {

	public static String columnName2FieldName(String columnName) {
		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
	}

}
