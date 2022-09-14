package codeforce.div2.r714;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 11/04/21
 */
public class B {

  private static final int MOD = 1000000000 + 7;

  public static void main(String[] args) {
    long[] modPrimes = buildModPrimes(2 * 100000 + 1);
    //318608048
    //641419708
    //System.out.println("50 prime " + modPrimes[50]);
    //System.out.println("1000 prime " + modPrimes[1000]);
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        Map<Integer, Integer> frequency = new HashMap<>(n);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          frequency.put(arr[i], frequency.getOrDefault(arr[i], 0) + 1);
          min = Math.min(min, arr[i]);
        }
        int allAnd = Arrays.stream(arr).reduce(min, (a, b) -> a & b);
        if (frequency.get(min) >= 2 && allAnd == min) {
          int num1 = frequency.get(min);
          int num2 = n - 2;
          long ans = mul(nc2(num1), modPrimes[num2]);
          System.out.println(ans);
        } else
          System.out.println(0);
      }
    }
  }

  private static long nc2(long num) {
    return mul(num, num - 1);
  }

  private static long[] buildModPrimes(int range) {
    long[] ans = new long[range + 1];
    long curMul = 1;
    ans[0] = 1;
    for (int i = 1; i < ans.length; i++) {
      curMul = mul(curMul, i);
      ans[i] = curMul;
    }
    return ans;
  }

  private static long mul(long a, long b) {
    return ((a % MOD) * (b % MOD)) % MOD;
  }
}