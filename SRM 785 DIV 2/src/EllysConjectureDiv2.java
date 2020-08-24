import java.util.*;
import java.math.*;

import static java.lang.Math.*;

public class EllysConjectureDiv2 {

	public static void main(String[] args) {

		System.out.println(new EllysConjectureDiv2().getSum(3, 6));
		System.out.println(new EllysConjectureDiv2().getSum(6, 9));
		System.out.println(new EllysConjectureDiv2().getSum(1, 3));
		System.out.println(new EllysConjectureDiv2().getSum(1, 2));
		System.out.println(new EllysConjectureDiv2().getSum(2, 4));
	}

	public long getSum(int L, int R) {

		long ans = 0;

		if (L < 4) {
			for (int i = L; i < 4 && i <= R; i++) {
				ans += i;
			}
		}
		if (R < 4)
			return ans;
		if (L < 4)
			L = 4;

		long n = R - L + 1;
		long num3 = n / 3;
		if(L %3 == 0 && R%3 ==0 )
			num3++;
		long num4 = n - num3;
		ans += num4 * 4 + num3 * 6;
		return ans;
	}

}
