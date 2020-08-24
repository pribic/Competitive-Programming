import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class CardboardBoxes {
	public static void main(String[] args) {
		System.out.println(new Solution().checkValidString("(*(()))((())())*(**(()))((*)()(()))*(())()(())(()"));
		int a=0;
		int b=1;
		int n = 100;
		long[] num = new long[n+1];
		Arrays.fill(num, -1);
		long t1=System.currentTimeMillis();
		System.out.println(fibonacci(n, num));
		long t2=System.currentTimeMillis();
		System.out.println(t2-t1 + ".............");
	}

	private static long fibonacci(int i, long[] num) {
		if (i == 0) {
			num[i] = 0;
			return 0;
		}
		else if (i == 1) {
			num[i] = 1;
			return 1;
		}
		long c = num[i];
		if (c == -1) {
			c = fibonacci(i - 1, num) + fibonacci(i - 2, num);
		}
		num[i] = c;
		return c;
	}

	static class Solution {
		public boolean checkValidString(String s) {
			Stack<Character> stack = new Stack<>();
			for(char c : s.toCharArray()) {
				if(c == '(' || c == '*')
					stack.push(c);
				else {
					if(!stack.empty() && stack.peek() == '(')
						stack.pop();
					else 
						stack.push(c);
				}
			}
			
			String ss = "";
			while (!stack.empty()) {
				ss = stack.pop() + ss;
			}
			return valid("", ss, 0, 0, ss.length());
		}
		private boolean valid(String str, String s, int index, int cnt, int len) {
			if( index == len)
				return cnt == 0 && validStr(str);
			if(s.charAt(index) == '(') {
				if(valid(str + "(", s, index + 1, cnt + 1, len))
					return true;
			} else if(s.charAt(index) == ')') {
				if(cnt <= 0)
					return false;
				if (valid(str + ")", s, index + 1, cnt - 1, len))
					return true;
			}
				else {
					if(valid(str + "(", s, index + 1, cnt + 1, len))
						return true;
					if(valid(str + ")", s, index + 1, cnt - 1, len))
						return true;
					if(valid(str, s, index + 1, cnt, len))
						return true;
				}
			return false;
		}

		private boolean validStr(String s) {
			Stack<Character> stack = new Stack<>();
			for(char c : s.toCharArray()) {
				if(c == '(' || c == '*')
					stack.push(c);
				else {
					if(!stack.empty() && stack.peek() == '(')
						stack.pop();
					else
						stack.push(c);
				}
			}
			return stack.empty();
		}
	}
}
