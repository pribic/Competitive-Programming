//package CodeJam2021_0410.AppendSort;
package codejam.A;
import java.math.BigInteger;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      System.out.print("Case #" + tt + ": ");
      int n = sc.nextInt();
      String[] arr = new String[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.next();
      }
      solve(n, arr);
    }
  }

  private static void solve(int n, String[] arr) {
    int count = 0;
    for (int i = 1; i < n; i++) {
      String current = arr[i];
      String previous = arr[i-1];
      if (new BigInteger(current).compareTo(new BigInteger(previous)) > 0) {
        continue;
      }
      String newCurr = getHigherNum(current, previous);
      count += newCurr.length() - current.length();
      arr[i] = newCurr;
    }
		/*for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		*/System.out.println(count);
  }

  private static String getHigherNum(String current, String previous) {
    if (new BigInteger(current).compareTo(new BigInteger(previous.substring(0, current.length())))  > 0) {
      while(current.length() < previous.length()) {
        current += "0";
      }
    } else if (new BigInteger(current).compareTo(new BigInteger(previous.substring(0, current.length()))) < 0) {
      while(current.length() <= previous.length()) {
        current += "0";
      }
    } else {
      if (current.length() == previous.length()) {
        current += "0";
      } else {
        String addString = previous.substring(current.length());
        String addNum = ((addString != null && !addString.trim().isEmpty()) ? new BigInteger(addString).add((new BigInteger("1"))) : new BigInteger("0")).toString();
        String parity = "";
        while((addNum+parity).length() < addString.length()) {
          parity += "0";
        }
        current += parity + addNum;
      }
      if (new BigInteger(current).compareTo(new BigInteger(previous)) <= 0) {
        current += "0";
      }
    }
    return current;
  }
}