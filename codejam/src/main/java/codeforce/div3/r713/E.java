package codeforce.div3.r713;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author pribic (Priyank Doshi)
 * @since 04/05/21
 */
public class E {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int l = sc.nextInt() - 1;
        int r = sc.nextInt() - 1;
        int cursum = sc.nextInt();
        int len = r - l + 1;
        int min = low(len);
        int max = high(n, len);
        if (cursum < min || cursum > max) {
          System.out.println(-1);
        } else {
        /*  1 to 10 -> 55 min 1 , max 55 is possible
            50 suppose - i need to include 10 because 1 to 9 sum upto 45 only
          now i need to solve for 40 and 1 to 9, now do i need 9 ? i do, because 1 to 8 sum up to 36 only
          so 9 is also fixed
            now we need 31 only , we need 8 too
          now we are at 23 , from 1 to 7 which is max 28, */
          ;
          Set<Integer> used = new HashSet<>();
          for (int i = n; i >= 1; i--) {
            //check can we take i or not.
            int newsum = cursum - i;
            //is it achievable
            min = low(len - 1);
            max = high(i - 1, len - 1);
            if (newsum < min || newsum > max)
              continue;
            used.add(i);
            len--;
            cursum = newsum;
          }
          int[] ans = new int[n];

          Set<Integer> total = new HashSet<>();
          for (int i = 1; i <= n; i++)
            total.add(i);
          total.removeAll(used);
          Iterator<Integer> usedItr = used.iterator();
          Iterator<Integer> totalItr = total.iterator();
          for (int i = 0; i < n; i++) {
            if (i >= l && i <= r) {
              ans[i] = usedItr.next();
            } else {
              ans[i] = totalItr.next();
            }
          }
          for (int num : ans)
            System.out.print(num + " ");
          System.out.println();
        }

      }
    }
  }

  static int low(int n) {
    return n * (n + 1) / 2;
  }

  static int high(int n, int k) {
    return k * (2 * n + 1 - k) / 2;
  }
}