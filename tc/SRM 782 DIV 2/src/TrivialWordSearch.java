import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class TrivialWordSearch {

	public static void main(String[] args) {
		/*
		String[] a = ( new TrivialWordSearch().construct("EEL"));
		for(String ss : a) {
			System.out.println(ss);
		}*/
	}

	public String[] construct(String w) {
		if(w.length() ==1)
			return new String[] {w};
		List<Character> ss = new ArrayList<>();
		for(char c : w.toCharArray()) {
			if(ss.contains(c))
				continue;
			ss.add(c);
		}
		if(ss.size() == 1)
			return new String[0];
		if(w.length() == 2 && ss.size() == 2)
			return new String[0];
		if(ss.size() > 2) {
			String[] and = new String[w.length()];
			StringBuilder dummy = new StringBuilder();
			for(int x = 0;  x < w.length(); x++)
				dummy.append(ss.get(2));
			and[0] = w;
			for(int j = 1; j < w.length(); j++) {
				and[j] = dummy.toString();
			}
			return and;
		} else {
			String[] and = new String[w.length()];
			and[0] = w;
			if(w.charAt(0) == w.charAt(1)) {
				StringBuilder dummy = new StringBuilder();
				for(int x = 0;  x < w.length(); x++)
					dummy.append(ss.get(1));
				for(int j=1; j< w.length(); j++){
					and[j] = dummy.toString();
				}
				//and[0] = w;
			} else {
				StringBuilder dummy = new StringBuilder();
				for(int x = 0;  x < w.length(); x++)
					dummy.append(ss.get(0));
				for(int j=1; j< w.length(); j++){
					and[j] = dummy.toString();
				}
			}
			return and;
		}
	}
}
