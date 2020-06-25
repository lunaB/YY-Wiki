package xyz.lunab.yywiki.util;

public class PermissionUtil {
	
	public static int check(String role) {
		switch(role) {
		case "":
			return 1;
		case "ROLE_USER":
			return 2;
		case "ROLE_STAFF":
			return 3;
		case "ROLE_ADMIN":
			return 4;
		}
		return 0;
	}
}
