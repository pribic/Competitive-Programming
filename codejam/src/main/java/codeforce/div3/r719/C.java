package codeforce.div3.r719;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 05/05/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        if(n == 2) {
          System.out.println(-1);
          continue;
        }
          
        int[] ans = new int[n * n];
        int val = 1;
        for (int idx = 0; idx < 2; idx++) {
          int start = idx;
          while (start < ans.length) {
            ans[start] = val;
            start += 2;
            val++;
          }
        }
        for (int i = 1; i <= n * n; i++) {
          System.out.print(ans[i - 1] + " ");
          if (i % n == 0)
            System.out.println();
        }
      }
    }
  }
}