import java.util.*;

public class Encoding
{
	//m is . n is -
	public static void main(String[] args) {
		morseCodes(2, 1);

	}
	
	public static Set<String> morseCodes(int m, int n)	{	public static Set<String> morseCodes(int m, int n)	{
    	String origin = new String();
		for(int i = 0; i < n; i++) {
			origin += "-";
	    }
		for(int j = 0; j < m; j++) {
			origin += ".";
		}
		ArrayList<String> permutations = perms(origin);
		Set<String> noRepeat = new TreeSet<String>(permutations);
		return noRepeat;
	}
	public static ArrayList<String> perms(String aSet) {
		ArrayList<String> permutations = new ArrayList<String>();
		if(aSet.length() == 1) {
			permutations.add(aSet);
		}else {
			String first = aSet.substring(0,1);
			String theRest = aSet.substring(1);
			permutations = merge(perms(theRest), first);
		}
		return permutations;
	}
	
	public static ArrayList<String> merge(ArrayList<String> pers, String insert) {
		ArrayList<String> result = new ArrayList<String>();
		for(String elem : pers) {
			for(int i = 0; i < elem.length(); i++) {
				String e = elem.substring(0, i) + insert + elem.substring(i);
				result.add(e);
			}
		}
		return result;
	}
    	String origin = new String();
		for(int i = 0; i < n; i++) {
			origin += "-";
	    }
		for(int j = 0; j < m; j++) {
			origin += ".";
		}
		
		ArrayList<String> permutations = perms(origin);
		Set<String> noRepeat = new TreeSet<String>(permutations);

		return noRepeat;
	}
	public static ArrayList<String> perms(String aSet) {
		ArrayList<String> permutations = new ArrayList<String>();
		if(aSet.length() == 1) {
			permutations.add(aSet);
		}else {
			String first = aSet.substring(0,1);
			String theRest = aSet.substring(1);
			permutations = merge(perms(theRest), first);
		}
		return permutations;
	}
	
	public static ArrayList<String> merge(ArrayList<String> pers, String insert) {
		ArrayList<String> result = new ArrayList<String>();
		for(String elem : pers) {
			for(int i = 0; i <= elem.length(); i++) {
				String e = elem.substring(0, i) + insert + elem.substring(i);
				result.add(e);
			}
		}
		return result;
	}
}