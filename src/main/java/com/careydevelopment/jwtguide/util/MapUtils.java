package com.careydevelopment.jwtguide.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.careydevelopment.jwtguide.model.User;


public class MapUtils {

	public static Map<String, Object> toKeyValuePairs(Object instance) {
	    return Arrays.stream(User.class.getDeclaredMethods())
	    		.filter(m -> !m.getName().startsWith("set"))
	            .filter(m -> !m.getName().startsWith("getClass"))
	            .filter(m -> !m.getName().startsWith("setClass"))
	            .filter(m -> !m.getName().startsWith("lambda"))
	            .collect(Collectors.toMap(
	            			m -> ReflectionUtils.getFieldFromMethod(m.getName()),
	            			m -> getValue(m, instance)
	                    )
	            );
	}
	
	
	private static Object getValue(Method m, Object instance) {
		Object result = null;
		
        try {
            result = m.invoke(instance);
            return result != null ? result : "";
        } catch (Exception e) {
            result = "";
        }
		
        return result;
	}
}
