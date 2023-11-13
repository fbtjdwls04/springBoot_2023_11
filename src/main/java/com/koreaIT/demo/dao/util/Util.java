package com.koreaIT.demo.dao.util;

public class Util {
	public static boolean empty(String str) {
		
		if(str == null) {
			return true;
		}
		
		return str.trim().length() == 0;
	}
	
	public static String f(String format, Object... args) {
		return String.format(format, args);
	}
}
