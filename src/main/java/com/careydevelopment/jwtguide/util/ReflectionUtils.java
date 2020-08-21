package com.careydevelopment.jwtguide.util;

public class ReflectionUtils {

	public static String getFieldFromMethod(String methodName) {
		String fieldName = methodName;
		
		if (methodName.startsWith("get")) {
			fieldName = methodName.substring(3, methodName.length());
		} else if (methodName.startsWith("is")) {
			fieldName = methodName.substring(2, methodName.length());			
		}

		fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);

		return fieldName;
	}

}
