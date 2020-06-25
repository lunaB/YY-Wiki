package xyz.lunab.yywiki.util;

public class XssUtil {
	
	public static String xssEncode(String text) {
		return text
			// 세미콜론 먼저 수정해야함
			// .replace(";", "&#59;") (여러개 되는 문제 때문에 잠깐 비활성)
			// 나머지 문법 사용 문자열 혹은 xss/injection 위험 문자 치환
			// xss 위험군
			.replace("<", "&#60;")
			.replace(">", "&#62;")
			.replace("\\", "&#92;")
			// 특수문자 문법 치환
			.replace("#", "&#35;")
			.replace("-", "&#45;")
			.replace("*", "&#42;")
			.replace("[", "&#91;")
			.replace("]", "&#93;")
			.replace("~", "&#126;")
			.replace("_", "&#95;")
			.replace("/", "&#47;")
			;
			
	}
	
	// 혹시나 해서 만들어 놓은 것 
//	public static String xssDecode(String text) {
//		return text
//		// 세미콜론 먼저 수정해야함
//		.replace("&#59;", ";")
//		// xss 위험군
//		.replace("&#60;", "<")
//		.replace("&#62;", ">")
//		.replace("&#92;", "\\")
//		// 특수문자 문법 치환
//		.replace("&#35;", "#")
//		.replace("&#45;", "-")
//		.replace("&#42;", "*")
//		.replace("&#91;", "[")
//		.replace("&#93;", "]")
//		.replace("&#126;", "~")
//		.replace("&#126;", "_")
//		.replace("&#47;", "/")
//		;
//	}
	
}
