import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class SmallestRegular {

	public static void main(String[] args) {
		System.out.println(new SmallestRegular().swap("()()", 1, 1, 2));
		System.out.println(new SmallestRegular().swap("(())()", 2, 3, 4));
		System.out.println(new SmallestRegular().swap("()(())", 1, 1, 3));
	}
	public int[] findLexSmallest(String S) {
		char[] input = S.toCharArray();
		List<Integer> ans = new ArrayList<>();
		for(int i = 0; i < input.length; i++) {
			int a = S.indexOf(')', i);
			int b = S.indexOf('(', a) - 1;
			if(b < 0)
				break;
			int c = S.indexOf(')', b + 1) - 1;
			ans.add(a);
			ans.add(b);
			ans.add(c);
			S = swap(S, a, b, c);
			i = a + 1;
		}
		int[] aa = new int[ans.size()];
		for(int i = 0; i < ans.size(); i++)
			aa[i] = ans.get(i);
		return aa;
	}

	private String swap(String s, int a, int b, int c) {
		String s1 = s.substring(0, a);
		String s2 = s.substring(a, b + 1);
		String s3 = s.substring(b+1, c + 1);
		String s4 = s.substring(c + 1);
		return s1 + s3 + s2 + s4;
	}
}
