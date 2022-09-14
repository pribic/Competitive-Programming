package Q2021.round1B.Subtransmutation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 25/04/21
 */
public class Solution {

  static int len = -1;

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int[] gems = new int[n + 1];
        for (int i = 0; i < n; i++) {
          gems[i + 1] = sc.nextInt();
        }
        len = n + 1;
        Map<Integer, long[]> memo = new HashMap<>();
        fill(1000, memo, a, b);
        for (int i = 999; i >= 1; i--)
          fill(i, memo, a, b);
        long max = -1;
        int cnt = 0;
        /*
        for(int aa = 1; aa <= 20; aa++) {
          for (int bb = aa + 1; bb <= 20; bb++) {
            Map<Integer, long[]> memo1 = new HashMap<>();
            System.out.println(aa + " aa and bb" + bb);
            fill(2000, memo1, aa, bb);
            boolean flag = false;
            for (int i = 1; i < 10000; i++) {
              if (check(i, memo1, aa, bb, new int[] {20, 20, 20, 20, 20, 20, 10, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20})) {
                System.out.println("aa bb " + aa + " " + bb + " ans " + i);
                max = Math.max(max, i);
                cnt++;
                flag = true;
                break;
              }
            }
            if(!flag) {
              System.out.println("no ans " + aa + " " + bb);
            }
          }
        }
        System.out.println("max is " + max + " and cnt is " + cnt); */
        boolean found = false;
        for (int i = 1; i < 1000; i++) {
          if (check(i, memo, a, b, gems)) {
            System.out.println(i);
            found = true;
            break;
          }
        }
        if (!found) {
          System.out.println("IMPOSSIBLE");
        }
      }
    }
  }

  private static boolean check(int num, Map<Integer, long[]> memo, int a, int b, int[] gems) {
    long[] needed = new long[21];
    for (int i = 0; i < gems.length; i++) {
      if (gems[i] > 0) {
        for (int j = 0; j < 21; j++) {
          if (memo.containsKey(i))
            needed[j] += memo.get(i)[j] * gems[i];
        }
      }
    }
    if (!memo.containsKey(num))
      return false;
    boolean valid = true;
    for (int i = 0; i < gems.length; i++) {
      if ((gems[i] > 0 && !memo.containsKey(i)) || needed[i] > 0 && needed[i] > memo.get(num)[i])
        valid = false;
    }
    return valid;
  }

  private static void multiply(long[] needed, int gem) {
    for (int i = 0; i < len; i++) {
      needed[i] *= gem;
    }
  }

  private static void add(long[] needed, long[] arr) {
    for (int i = 0; i < len; i++) {
      needed[i] += arr[i];
    }
  }

  private static long[] fill(int num, Map<Integer, long[]> memo, int a, int b) {
    if (num <= 0)
      return new long[21];
    if (memo.containsKey(num))
      return memo.get(num);
    if (num <= Math.min(a, b)) {
      long[] ans = new long[21];
      ans[num]++;
      memo.put(num, ans);
      return ans;
    }
    long[] left = fill(num - a, memo, a, b);
    long[] right = fill(num - b, memo, a, b);
    long[] cur = new long[21];
    for (int i = 0; i < 21; i++)
      cur[i] = left[i] + right[i];
    memo.put(num, cur);
    return cur;
  }
}
