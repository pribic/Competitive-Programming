package codeforce.div2.r694;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 05/01/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] arr = new int[n];
        long sum = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          sum += arr[i];
          max += (arr[i] + x - 1) / x;
        }
        long min = (sum + x - 1) / x;
        System.out.println(min + " " + max);
      }
      // 5 5
    }
  }
}