package com.kuwon.stugether.common;

import java.util.regex.Pattern;

public class RegExpUtils {
	public static final String REGEXP_ID = "^[a-z0-9]{8,16}$";
	public static final String REGEXP_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
	public static final String REGEXP_EMAIL = "^[a-z0-9]+@[a-z]+\\.[a-z]{2,3}$";
	public static final String REGEXP_NICKNAME = "^[a-z0-9가-힣]{2,10}$";
	
	// input : 검사할 문자열   format : 정규식
	public static boolean isValid(String regExp, String input) {
		return Pattern.matches(regExp, input);
	}
}