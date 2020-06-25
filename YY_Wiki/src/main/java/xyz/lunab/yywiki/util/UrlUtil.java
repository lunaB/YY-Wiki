package xyz.lunab.yywiki.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlUtil {
	
	public static String urlEncode(String text) throws UnsupportedEncodingException {
		return URLEncoder.encode(text, "UTF-8")
//				+가 기본으로 되는 방식 제거
				.replace("+", "%20")
//				예쁜 문자셋을 유지하기 위하여
				.replace("%3A", ":");
	}
	public static String urlDecode(String text) throws UnsupportedEncodingException {
		return URLDecoder.decode(text, "UTF-8");
	}
}
