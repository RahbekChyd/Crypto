
public class Utility {
	public static String padding(String s) {
		String result = s;
		while (result.length() < 3) {
			result = "0" + result;
		}
		return result;
	}
}
