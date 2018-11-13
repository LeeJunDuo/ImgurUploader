package jun.demo;

import org.springframework.util.DigestUtils;

public class Utils {
	public static String md5(String source) {
		return DigestUtils.md5DigestAsHex(source.toString().getBytes());
	}
}
