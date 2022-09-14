package kickstart.Y2021.round1A.KGoodnessString;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 21/03/21
 */
public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int k = sc.nextInt();
        String str = sc.next();
        int cnt = 0;
        for (int i = 0; i < n / 2; i++) {
          if (str.charAt(i) != str.charAt(n - i - 1))
            cnt++;
        }
        System.out.println(Math.abs(k - cnt));
      }
    }
  }
}