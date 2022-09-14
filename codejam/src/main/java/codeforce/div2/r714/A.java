package codeforce.div2.r714;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 11/04/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        // 1 4 2 5 3 -> 5 len , 2 peak -> 5/2
        // 1 8 2 7 3 4 5 6-> 6 len, 3 peak -> 6/2
        int max = (n - 1) / 2;
        if (k > max ) {
          System.out.println(-1);
        } else {
          int[] ans = new int[n];
          for (int i = 0; i < k; i++) {
            ans[2 * i + 1] = n;
            n--;
          }
          for(int i = 0; i < k; i++) {
            ans[2*i] = i + 1;
          }
          int st = k + 1;
          for(int i = 2*k; i < ans.length; i++) {
            ans[i] = st;
            st++;
          }
          for(int num : ans) {
            System.out.print(num + " ");
          }
          System.out.println();
        }
      }
    }
  }
}