
public class Strings {
	public static String uniqueLetters(String str)	{
		int i;
		int pos;
		int length = str.length();
		int[] ascii = new int[26];
		String result = new String();
		for(i = 0; i < length; i++) {
			pos = str.charAt(i);
			pos -= 97;
			ascii[pos]++;
		}
			
		for(i = 0; i < length; i++) {
			pos = str.charAt(i);
			pos -= 97;
			if(ascii[pos] == 1) {
				result += str.substring(i,i+1);
			}
		}
		return result;
	  }
}
