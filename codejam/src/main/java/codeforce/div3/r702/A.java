package codeforce.div3.r702;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 16/02/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        long ans = 0;
        for (int i = 0; i < n - 1; i++) {
          int min = Math.min(arr[i], arr[i + 1]);
          int max = Math.max(arr[i], arr[i + 1]);
          while (max > 2 * min) {
            ans++;
            min *= 2;
          }
        }
        System.out.println(ans);
      }
    }
  }
}