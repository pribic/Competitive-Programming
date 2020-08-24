import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class AqaAsadiPlays {

	Map<String, Integer> gcdMap = new HashMap<>();

	int[] gcdMemo;

	public int getMin(int[] A) {
		Arrays.sort(A);
		gcdMemo = new int[A.length];
		preceedGcd(A);
		int ans = -1;
		Set<Integer> removed = new HashSet<>();
		for (int i = 0; i < A.length; i++) {
			int gcd;
			if(i == A.length - 1) {
				gcd = A[A.length - 1];
			}
			else
				gcd =  gcdMemo[i + 1];
			//check if gcd is higher than previous elements
			int cnt = 0;
			for (int j = 0; j <= i; j++) {
				if (gcd > A[j])
					cnt++;
			}
			if (cnt == (i + 1))
				return A.length - i - 1;
		}
		return -1;
	}

	void preceedGcd(int[] arr) {
		int ans = arr[arr.length - 1];
		gcdMemo[arr.length - 1] = ans;
		for (int i = arr.length - 1 ;  i >= 0; i--) {
			ans = preceedGcd(ans, arr[i]);
			gcdMemo[i] = ans;
		}
	}

	int preceedGcd(int a, int b) {
		if(gcdMap.containsKey(a + ":" + b))
			return gcdMap.get(a + ":" + b);
		if (b == 0)
			return a;
		int temp =  preceedGcd(b, a % b);
		gcdMap.put("a" + ":" + b, temp);
		return temp;
	}
}
