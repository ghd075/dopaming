package com.dopaming.www.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImgExtract {
	public static String getfirstimage(String text) {

		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); // img 태그 src 추출 정규표현식
		Matcher matcher = pattern.matcher(text);

		if (matcher.find()) {
			return matcher.group(1);
		}
		return "";
	}
}
