package xyz.lunab.yywiki.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlInjectionUtil {
	
	private final static Pattern SpecialChars = Pattern.compile("['\"\\-#()@;=*/+]");
	private final static String regex = "(union|select|from|where)";
	
	private final static Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	
	public static String replace(String text) {
		text = SpecialChars.matcher(text).replaceAll("");
		
		return text;
	}
	
	public static boolean isDenger(String text) {
		Matcher matcher = pattern.matcher(text);
		if(matcher.find()){
			return true;
		}else {
			return false;
		}
	}
}
