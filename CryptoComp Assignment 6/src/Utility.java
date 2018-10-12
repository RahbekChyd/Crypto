
public class Utility {
	public static int[] binaryInt(int s) {
		String result = Integer.toBinaryString(s);
		while (result.length() < 3) {
			result = "0" + result;
		}
		int res[] = new int[3];
		res[0] = Integer.parseInt(result.substring(0, 1));
		res[1] = Integer.parseInt(result.substring(1, 2));
		res[2] = Integer.parseInt(result.substring(2, 3));
		return res;
	}
}
